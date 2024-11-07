package org.lesson19;

import jakarta.persistence.LockModeType;
import org.hibernate.cfg.Configuration;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// optimistic - 3591
// pessimistic - 1744

public class Main {
    public static void main(String[] args) {
        {
            TableCreator.createAndFillTables();
            long t1 = System.currentTimeMillis();
            run(LockModeType.OPTIMISTIC);
            long t2 = System.currentTimeMillis();
            System.out.println("Time = " + (t2 - t1));
        }
        {
            TableCreator.createAndFillTables();
            long t1 = System.currentTimeMillis();
            run(LockModeType.PESSIMISTIC_WRITE);
            long t2 = System.currentTimeMillis();
            System.out.println("Time = " + (t2-t1));
        }
    }

    public static void run(LockModeType mode) {
        var pool = Executors.newFixedThreadPool(8);
        {
            for (long i = 0L; i < 8L; ++i) {
                long ind = i;
                pool.execute(() -> {
                    Random rnd = new Random();
                    try (var factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {
                        try (var session = factory.getCurrentSession()) {
                            session.getTransaction().begin();
                            User currUser = session.get(User.class, ind);
                            session.getTransaction().commit();
                            for (int j = 0; j < 1000; ++j) {
                                long lotInd = Math.abs(rnd.nextLong() % 4);
                                boolean success = false;
                                while (!success) {
                                    session.getTransaction().begin();
                                    Lot currLot = session.createQuery("SELECT l FROM Lot l WHERE l.id = :id", Lot.class)
                                            .setParameter("id", lotInd)
                                            .setLockMode(mode)
                                            .getSingleResult();
                                    currLot.setPrice(currLot.getPrice() + 100);
                                    currLot.setUser(currUser);
                                    try {
                                        session.getTransaction().commit();
                                        success = true;
                                    } catch (Exception e) {
                                        session.getTransaction().rollback();
                                    }
                                }
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        }
        try {
            pool.shutdown();
            pool.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
