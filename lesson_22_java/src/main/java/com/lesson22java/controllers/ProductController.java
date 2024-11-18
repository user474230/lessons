package com.lesson22java.controllers;

import com.lesson22java.entities.Product;
import com.lesson22java.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @GetMapping("/create")
    public Product create(@ModelAttribute Product product) {
        return productService.create(product);
    }

    @GetMapping("/getAll")
    public Iterable<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/getOne/{id}")
    public Product getOne(@PathVariable Long id) {
        return productService.getOne(id);
    }

    @GetMapping("/edit")
    public Product edit(@ModelAttribute Product product) {
        return productService.edit(product);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}
