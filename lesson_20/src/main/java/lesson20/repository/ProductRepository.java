package lesson20.repository;

import lesson20.entities.Product;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepository {
    private SessionFactory sessionFactory;
    public ProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Product product) {
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            session.persist(product);
            session.getTransaction().commit();
        }
    }

    public Product getById(Long id) {
        Product ret = null;
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            ret = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return ret;
    }

    public List<Product> getAll() {
        List<Product> ret = null;
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            ret = session.createQuery("SELECT p FROM Product p", Product.class).getResultList();
            session.getTransaction().commit();
        }
        return ret;
    }
}
