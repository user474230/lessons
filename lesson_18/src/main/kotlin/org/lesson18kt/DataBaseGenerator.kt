package org.lesson18kt

import org.hibernate.cfg.Configuration
import java.math.BigDecimal
import kotlin.random.Random

fun generateDataBase() {
    Configuration().configure("hibernate2.cfg.xml").buildSessionFactory().use { factory ->
        factory.currentSession.use { session ->
            session.transaction.begin()

            session.createNativeQuery(
                """
                   CREATE TABLE IF NOT EXISTS purchase_details (
                           customer_id uuid NULL,
                   product_id uuid NULL,
                   price numeric(15, 2) NULL,
                   count varchar NULL,
                   id uuid NOT NULL,
                   CONSTRAINT purchase_details_pk PRIMARY KEY (id)
                   );
            """.trimIndent()
            ).executeUpdate()

            session.createNativeQuery(
                """
                   CREATE TABLE IF NOT EXISTS product (
                           id uuid NOT NULL,
                   title text NULL,
                   price numeric(15, 2) NULL,
                   CONSTRAINT product_pk PRIMARY KEY (id)
                   );
            """.trimIndent()
            ).executeUpdate()

            session.createNativeQuery(
                """
                   CREATE TABLE IF NOT EXISTS customer (
                            id uuid NOT NULL,
                   fio text NULL,
                   CONSTRAINT customer_pk PRIMARY KEY (id)
                   );
            """.trimIndent()
            ).executeUpdate()
            session.flush();

            session.createNativeQuery("DELETE FROM purchase_details;").executeUpdate()
            session.createNativeQuery("DELETE FROM product;").executeUpdate()
            session.createNativeQuery("DELETE FROM customer;").executeUpdate()
            session.flush()

            val customers = listOf(
                Customer(fio = "Иван"),
                Customer(fio = "Антон"),
                Customer(fio = "Владимир")
            )
            customers.forEach { session.persist(it) }

            val products = listOf(
                Product(title = "Карандаш", price = BigDecimal.valueOf(22.02)),
                Product(title = "Ручка", price = BigDecimal.valueOf(42.42)),
                Product(title = "Папка", price = BigDecimal.valueOf(98.55))
            )
            products.forEach { session.persist(it) }

            val details = List<PurchaseDetails>(10) {
                val productInd = Random.nextInt(0, products.size);
                val customerInd = Random.nextInt(0, customers.size);
                val count = Random.nextLong(0, 10);
                val customer = customers[customerInd]
                val product = products[productInd]

                PurchaseDetails(
                    customerId = customer.id,
                    productId = product.id,
                    price = product.price,
                    count = count
                )
            }
            details.forEach { session.persist(it) }

            session.transaction.commit()
        }
    }
}