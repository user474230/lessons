package com.lesson22.controllers

import com.lesson22.entities.Customer
import com.lesson22.services.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController (val customerService: CustomerService) {
    @GetMapping("/get-all")
    fun getAll() = customerService.getAll()

    @GetMapping("/create")
    fun create(@ModelAttribute customer: Customer) = customerService.create(customer)

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) = customerService.delete(id)

    @GetMapping("/edit")
    fun edit(@ModelAttribute customer: Customer) = customerService.edit(customer)
}