package lesson20.repository;

import lesson20.entities.Customer;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerRepository {
    private SessionFactory sessionFactory;
    public CustomerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Customer customer) {
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            session.persist(customer);
            session.getTransaction().commit();
        }
    }

    public Customer getById(Long id) {
        Customer ret = null;
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            ret = session.get(Customer.class, id);
            session.getTransaction().commit();
        }
        return ret;
    }

    public List<Customer> getAll() {
        List<Customer> ret = null;
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            ret = session.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
            session.getTransaction().commit();
        }
        return ret;
    }
}
