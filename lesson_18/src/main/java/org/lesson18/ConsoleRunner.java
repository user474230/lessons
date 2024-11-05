package org.lesson18;


// /buy id_покупателя id_товара - реализовать возможность “покупки товара” по id покупателя и товара.

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;
import java.util.UUID;

public class ConsoleRunner {
    public static void showProductsByConsumer(SessionFactory factory, String fio) {
        try (var session = factory.getCurrentSession()) {
            session.getTransaction().begin();
            var list = session.createQuery("SELECT c FROM Customer c WHERE c.fio = :fio", Customer.class)
                    .setParameter("fio", fio)
                    .getResultList();
            if (list.isEmpty()) {
                System.out.println("Не найдено покупателей с именем " + fio);
            } else {
                list.forEach(customer -> {
                    System.out.println("Список покупок для " + fio + " c id " + customer.getId());
                    customer.getProducts().forEach(product ->
                            System.out.println(product.getTitle()));
                });
                System.out.println();
            }
            session.getTransaction().commit();
        }
    }

    public static void showConsumersByProductTitle(SessionFactory factory, String title) {
        try (var session = factory.getCurrentSession()) {
            session.getTransaction().begin();
            var list = session.createQuery("SELECT c FROM Product c WHERE c.title = :title", Product.class)
                    .setParameter("title", title)
                    .getResultList();
            if (list.isEmpty()) {
                System.out.println("Не найдено товаров с названием " + title);
            } else {
                list.forEach(product -> {
                    System.out.println("Список покупателей для " + title + " c id " + product.getId());
                    product.getCustomers().forEach(customer ->
                            System.out.println(customer.getFio()));
                });
                System.out.println();
            }
            session.getTransaction().commit();
        }
    }

    public static void deleteConsumer(SessionFactory factory, String fio) {
        try (var session = factory.getCurrentSession()) {
            session.getTransaction().begin();
            var list = session.createQuery("SELECT c FROM Customer c WHERE c.fio = :fio", Customer.class)
                    .setParameter("fio", fio)
                    .getResultList();
            list.forEach(session::delete);
            session.getTransaction().commit();
        }
    }

    public static void deleteProduct(SessionFactory factory, String title) {
        try (var session = factory.getCurrentSession()) {
            session.getTransaction().begin();
            var list = session.createQuery("SELECT c FROM Product c WHERE c.title = :title", Product.class)
                    .setParameter("title", title)
                    .getResultList();
            list.forEach(session::delete);
            session.getTransaction().commit();
        }
    }

    public static void buy(SessionFactory factory, String consumerId, String productId, String count) {
        try (var session = factory.getCurrentSession()) {
            session.getTransaction().begin();
            Product product = session.get(Product.class, UUID.fromString(productId));

            PurchaseDetails pd = new PurchaseDetails(product.getId(), UUID.fromString(consumerId), product.getPrice(), Long.valueOf(count));
            session.persist(pd);
            session.getTransaction().commit();
        }
    }

    public static void run() {
        try (var factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {

            while (true) {
                Scanner s = new Scanner(System.in);
                var args = s.nextLine().split(" ");
                switch (args[0]) {
                    case "/showProductsByConsumer" -> {
                        if (args.length != 2) {
                            System.out.println("У команды /showProductsByConsumer должен быть 1 аргумент");
                            continue;
                        }
                        showProductsByConsumer(factory, args[1]);
                    }
                    case "/showConsumersByProductTitle" -> {
                        if (args.length != 2) {
                            System.out.println("У команды /showConsumersByProductTitle должен быть 1 аргумент");
                            continue;
                        }
                        showConsumersByProductTitle(factory, args[1]);
                    }
                    case "/deleteConsumer" -> {
                        if (args.length != 2) {
                            System.out.println("У команды /deleteConsumer должен быть 1 аргумент");
                            continue;
                        }
                        deleteConsumer(factory, args[1]);
                    }
                    case "/deleteProduct" -> {
                        if (args.length != 2) {
                            System.out.println("У команды /deleteProduct должен быть 1 аргумент");
                            continue;
                        }
                        deleteProduct(factory, args[1]);
                    }
                    case "/buy" -> {
                        if (args.length != 4) {
                            System.out.println("У команды /buy должен быть 1 аргумент");
                            continue;
                        }
                        buy(factory, args[1], args[2], args[3]);
                    }
                    case "/quit" -> {
                        return;
                    }
                    default -> {
                        System.out.println("Неизвестная команда: " + args[0]);
                    }
                }

            }
        }
    }
}
