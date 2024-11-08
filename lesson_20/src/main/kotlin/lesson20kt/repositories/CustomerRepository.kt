package lesson20kt.repositories

import lesson20kt.entities.Customer
import org.hibernate.SessionFactory
import org.springframework.stereotype.Component

@Component
class CustomerRepository(val sessionFactory: SessionFactory) {

    fun save(customer: Customer) = sessionFactory.currentSession.use { session ->
        session.transaction.begin()
        session.save(customer)
        session.transaction.commit()
    }

    fun load(id: Long): Customer = sessionFactory.currentSession.use { session ->
        session.transaction.begin()
        val ret = session.get(Customer::class.java, id)
        session.transaction.commit()
        return ret
    }

    fun loadAll(): List<Customer> = sessionFactory.currentSession.use { session ->
        session.transaction.begin()
        val ret = session.createQuery("SELECT c FROM Customer c", Customer::class.java).list()
        session.transaction.commit()
        return ret
    }
}