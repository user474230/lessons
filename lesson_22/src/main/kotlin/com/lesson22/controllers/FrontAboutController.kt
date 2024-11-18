package com.lesson22.controllers

import com.lesson22.services.ProductService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/front")
class FrontAboutController (val productService: ProductService) {
    @GetMapping("/about")
    fun about() : String {
        return "about-page"
    }
}