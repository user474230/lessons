package lesson20kt.repositories

import lesson20kt.entities.Product
import org.hibernate.SessionFactory
import org.springframework.stereotype.Component

@Component
class ProductRepository(val sessionFactory: SessionFactory) {

    fun save(product: Product) = sessionFactory.currentSession.use { session ->
        session.transaction.begin()
        session.save(product)
        session.transaction.commit()
    }

    fun load(id: Long): Product = sessionFactory.currentSession.use { session ->
        session.transaction.begin()
        val ret = session.get(Product::class.java, id)
        session.transaction.commit()
        return ret
    }

    fun loadAll(): List<Product> = sessionFactory.currentSession.use { session ->
        session.transaction.begin()
        val ret = return session.createQuery("SELECT p FROM Product p", Product::class.java).list()
        session.transaction.commit()
        return ret
    }
}