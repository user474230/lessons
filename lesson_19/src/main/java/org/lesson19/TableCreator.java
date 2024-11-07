package org.lesson19;

import org.hibernate.cfg.Configuration;

public class TableCreator {
    final static String sqlCreateLotTable = """
                    CREATE TABLE IF NOT EXISTS lot (
                    	id int8 NOT NULL,
                    	title text NULL,
                    	price int NULL,
                    	"version" int8 NULL,
                    	user_id int8 NULL,
                    	CONSTRAINT lot_pk PRIMARY KEY (id)
                    );                
                    DELETE FROM lot;
            """;


    final static String sqlCreateUserTable = """
                CREATE TABLE IF NOT EXISTS my_user (
                        	id int8 NOT NULL,
                        	fio text NULL,
                        	CONSTRAINT user_pk PRIMARY KEY (id)
                        );        
                DELETE FROM my_user;    
            """;

    public static void createAndFillTables() {
        try (var factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {
            try (var session = factory.getCurrentSession()) {
                session.getTransaction().begin();
                session.createNativeQuery(sqlCreateLotTable).executeUpdate();
                session.createNativeQuery(sqlCreateUserTable).executeUpdate();
                for (long i = 0L; i < 8L; ++i) {
                    session.save(new User(i, "user %d".formatted(i)));
                }
                for (long i = 0L; i < 4L; ++i) {
                    session.save(new Lot(i, "lot %d".formatted(i)));
                }
                session.getTransaction().commit();
            }
        }
    }
}
