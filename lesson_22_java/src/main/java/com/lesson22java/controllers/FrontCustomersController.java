package com.lesson22java.controllers;

import com.lesson22java.entities.Customer;
import com.lesson22java.services.CustomerService;
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
public class FrontCustomersController {
    private CustomerService customerService;

    @GetMapping("/customers-all")
    public String customersAll(){
        return "customers-all";
    }

    @GetMapping("/customers-delete/{id}")
    public String customersDelete(@PathVariable Long id) {
        customerService.delete(id);
        return "redirect:/front/customers-all";
    }

    @GetMapping("/customers-edit/{id}")
    public String customersDelete(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.getOne(id));
        return "customers-edit";
    }

    @GetMapping("/customers-edit-finish")
    public String customersEditFinish(@ModelAttribute Customer customer) {
        customerService.edit(customer);
        return "redirect:/front/customers-all";
    }

    @GetMapping("/customers-add-finish")
    public String customersAddFinish(@ModelAttribute Customer customer) {
        customerService.create(customer);
        return "redirect:/front/customers-all";
    }

    @GetMapping("/customers-add")
    public String customersAdd() {
        return "customers-add";
    }
}

