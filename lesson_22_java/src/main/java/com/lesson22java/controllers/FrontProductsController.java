package com.lesson22java.controllers;

import com.lesson22java.entities.Product;
import com.lesson22java.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front")
@AllArgsConstructor
public class FrontProductsController {
    private ProductService productService;

    @GetMapping("/products-all")
    public String productsAll() {
        return "products-all";
    }

    @GetMapping("/products-delete/{id}")
    public String productsDelete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/front/products-all";
    }

    @GetMapping("/products-edit/{id}")
    public String productsDelete(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getOne(id));
        return "products-edit";
    }

    @GetMapping("/products-edit-finish")
    public String productsEditFinish(@ModelAttribute Product product) {
        productService.edit(product);
        return "redirect:/front/products-all";
    }

    @GetMapping("/products-add-finish")
    public String productsAddFinish(@ModelAttribute Product product) {
        productService.create(product);
        return "redirect:/front/products-all";
    }

    @GetMapping("/products-add")
    public String productsAdd() {
        return "products-add";
    }
}



//class FrontProductsController (val productService: ProductService) {


//}