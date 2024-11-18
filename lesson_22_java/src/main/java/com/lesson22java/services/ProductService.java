package com.lesson22java.services;

import com.lesson22java.entities.Product;
import com.lesson22java.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getOne(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("No such product"));
    }

    public Product edit(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
