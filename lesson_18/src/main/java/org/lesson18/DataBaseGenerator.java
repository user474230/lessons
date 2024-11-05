package org.lesson18;

import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataBaseGenerator {
    public static void generate() {
        try (var factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {
            try (var session = factory.getCurrentSession()) {
                session.getTransaction().begin();
                session.createNativeQuery("""
                        CREATE TABLE IF NOT EXISTS purchase_details (
                        	customer_id uuid NULL,
                        	product_id uuid NULL,
                        	price numeric(15, 2) NULL,
                        	count varchar NULL,
                        	id uuid NOT NULL,
                        	CONSTRAINT purchase_details_pk PRIMARY KEY (id)
                        );
                        """).executeUpdate();

                session.createNativeQuery("""
                        CREATE TABLE IF NOT EXISTS product (
                        	id uuid NOT NULL,
                        	title text NULL,
                        	price numeric(15, 2) NULL,
                        	CONSTRAINT product_pk PRIMARY KEY (id)
                        );
                        """).executeUpdate();

                session.createNativeQuery("""
                        CREATE TABLE IF NOT EXISTS customer (
                            id uuid NOT NULL,
                            fio text NULL,
                            CONSTRAINT customer_pk PRIMARY KEY (id)
                        );
                        """).executeUpdate();
                session.flush();

                session.createQuery("DELETE FROM Customer").executeUpdate();
                session.createQuery("DELETE FROM Product").executeUpdate();
                session.createQuery("DELETE FROM PurchaseDetails").executeUpdate();
                session.flush();

                List<Customer> customers = List.of(
                        new Customer("Иван"),
                        new Customer("Петр"),
                        new Customer("Антон")
                );
                customers.forEach(session::persist);
                session.flush();

                List<Product> products = List.of(
                        new Product("Ручка", BigDecimal.valueOf(45.32)),
                        new Product("Карандаш", BigDecimal.valueOf(23.21)),
                        new Product("Папка", BigDecimal.valueOf(98.80))
                );
                products.forEach(session::persist);
                session.flush();

                List<PurchaseDetails> purchaseDetails = new ArrayList<>();
                Random rnd = new Random();
                for (int i = 0; i < 10; ++i) {
                    int indProduct = rnd.nextInt(products.size());
                    int indCustomer = rnd.nextInt(customers.size());
                    long count = rnd.nextInt(9) + 1;

                    var product = products.get(indProduct);
                    var customer = customers.get(indCustomer);
                    purchaseDetails.add(new PurchaseDetails(product.getId(), customer.getId(), product.getPrice(), count));
                }
                purchaseDetails.forEach(session::persist);
                session.flush();

                session.getTransaction().commit();
            }
        }
    }
}
