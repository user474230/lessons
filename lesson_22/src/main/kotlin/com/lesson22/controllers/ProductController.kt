package com.lesson22.controllers

import com.lesson22.entities.Product
import com.lesson22.services.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController (val productService: ProductService) {
    @GetMapping("/get-all")
    fun getAll() = productService.getAll()

    @GetMapping("/create")
    fun create(@ModelAttribute product: Product) = productService.create(product)

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) = productService.delete(id)

    @GetMapping("/edit")
    fun edit(@ModelAttribute product: Product) = productService.edit(product)
}