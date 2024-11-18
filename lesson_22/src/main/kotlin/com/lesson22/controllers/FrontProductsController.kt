package com.lesson22.controllers

import com.lesson22.entities.Product
import com.lesson22.services.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/front")
class FrontProductsController (val productService: ProductService) {
    @GetMapping("/products-all")
    fun productsAll() = "products-all"

    @GetMapping("/products-delete/{id}")
    fun productsDelete(@PathVariable id: Long) : String {
        productService.delete(id)
        return "redirect:/front/products-all"
    }

    @GetMapping("/products-edit/{id}")
    fun productsDelete(@PathVariable id: Long, model: Model) : String {
        model.addAttribute("product", productService.getById(id))
        return "products-edit"
    }

    @GetMapping("/products-edit-finish")
    fun productsEditFinish(@ModelAttribute product : Product) : String {
        productService.edit(product)
        return "redirect:/front/products-all"
    }

    @GetMapping("/products-add-finish")
    fun productsAddFinish(@ModelAttribute product : Product) : String {
        productService.create(product)
        return "redirect:/front/products-all"
    }

    @GetMapping("/products-add")
    fun productsAdd() : String {
        return "products-add"
    }
}