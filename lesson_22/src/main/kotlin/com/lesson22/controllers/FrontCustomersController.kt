package com.lesson22.controllers

import com.lesson22.entities.Customer
import com.lesson22.services.CustomerService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/front")
class FrontCustomersController (val customersService: CustomerService) {
    @GetMapping("/customers-all")
    fun customersAll() = "customers-all"

    @GetMapping("/customers-delete/{id}")
    fun customersDelete(@PathVariable id: Long) : String {
        customersService.delete(id)
        return "redirect:/front/customers-all"
    }

    @GetMapping("/customers-edit/{id}")
    fun customersDelete(@PathVariable id: Long, model: Model) : String {
        model.addAttribute("customer", customersService.getById(id))
        return "customers-edit"
    }

    @GetMapping("/customers-edit-finish")
    fun customersEditFinish(@ModelAttribute customer : Customer) : String {
        customersService.edit(customer)
        return "redirect:/front/customers-all"
    }

    @GetMapping("/customers-add-finish")
    fun customersAddFinish(@ModelAttribute customer : Customer) : String {
        customersService.create(customer)
        return "redirect:/front/customers-all"
    }

    @GetMapping("/customers-add")
    fun customersAdd() : String {
        return "customers-add"
    }
}