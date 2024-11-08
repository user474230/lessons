package org.example.lesson20kt

import lesson20kt.configs.Config
import lesson20kt.services.CustomerService
import lesson20kt.services.ProductService
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main() {
    var context = AnnotationConfigApplicationContext(Config::class.java)
    val customerService = context.getBean("customerService", CustomerService::class.java);
    val productService = context.getBean("productService", ProductService::class.java);

//    customerService.save(Customer(fio = "qwer", age = 20))
//    customerService.save(Customer(fio = "asdf", age = 21))
//    customerService.save(Customer(fio = "zxcv", age = 22))
//
//    productService.save(Product(title = "poiue", price = 100))
//    productService.save(Product(title = "hggfw", price = 200))
//    productService.save(Product(title = "jtrew", price = 300))

    println(customerService.load(1L))
    println(productService.load(1L))
    println(customerService.loadAll())
    println(productService.loadAll())
}