package org.lesson18kt

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import java.util.UUID


// /buy id_покупателя id_товара - реализовать возможность “покупки товара” по id покупателя и товара.

fun run() = Configuration().configure("hibernate2.cfg.xml").buildSessionFactory().use { factory ->
    while (true) {
        val args = readLine()?.split(" ")
        args?.let {
            when (args[0]) {
                "/showProductsByConsumer" -> showProductsByConsumer(factory, args)
                "/showConsumersByProductTitle" -> showConsumersByProductTitle(factory, args)
                "/deleteConsumer" -> deleteConsumer(factory, args)
                "/deleteProduct" -> deleteProduct(factory, args)
                "/buy" -> buy(factory, args)
                "/quit" -> return
                else -> println("Ошибка в команде")
            }
        }
    }
}

fun showProductsByConsumer(factory: SessionFactory, args: List<String>) = factory.currentSession.use { session ->
    val fio = args[1]

    session.transaction.begin()
    session.createQuery("SELECT c FROM Customer c WHERE c.fio = :fio", Customer::class.java)
        .setParameter("fio", fio)
        .resultList
        .forEach { customer ->
            println("Список товаров для ${customer.fio} с id = ${customer.id}")
            customer.products?.forEach { product ->
                println(product.title)
            }
        }

    session.transaction.commit()
}

fun showConsumersByProductTitle(factory: SessionFactory, args: List<String>) = factory.currentSession.use { session ->
    val title = args[1]

    session.transaction.begin()
    session.createQuery("SELECT p FROM Product p WHERE p.title = :title", Product::class.java)
        .setParameter("title", title)
        .resultList
        .forEach { product ->
            println("Список покупателей для ${product.title} с id = ${product.id}")
            product.customers?.forEach { customer ->
                println(customer.fio)
            }
        }

    session.transaction.commit()
}

fun deleteConsumer(factory: SessionFactory, args: List<String>) = factory.currentSession.use { session ->
    val fio = args[1]

    session.transaction.begin()
    session.createQuery("SELECT c FROM Customer c WHERE c.fio = :fio", Customer::class.java)
        .setParameter("fio", fio)
        .resultList
        .forEach { session.remove(it) }

    session.transaction.commit()
}

fun deleteProduct(factory: SessionFactory, args: List<String>) = factory.currentSession.use { session ->
    val title = args[1]

    session.transaction.begin()
    session.createQuery("SELECT p FROM Product p WHERE p.title = :title", Product::class.java)
        .setParameter("title", title)
        .resultList
        .forEach { session.remove(it) }

    session.transaction.commit()
}

fun buy(factory: SessionFactory, args: List<String>) = factory.currentSession.use { session ->
    val customerId = UUID.fromString(args[1])
    val productId = UUID.fromString(args[2])
    val count = args[3].toLong()

    session.transaction.begin()
    session.find(Product::class.java, productId)?.let { product ->
        session.persist(
            PurchaseDetails(
                customerId = customerId,
                productId = productId,
                price = product.price,
                count = count
            )
        )
    }

    session.transaction.commit()
}
