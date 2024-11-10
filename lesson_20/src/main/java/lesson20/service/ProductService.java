package lesson20.service;

import lesson20.entities.Product;
import lesson20.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    ProductRepository productRepository;
    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void create(Product product) {
        productRepository.create(product);
    }

    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }
}
