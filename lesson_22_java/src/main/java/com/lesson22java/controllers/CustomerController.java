package com.lesson22java.controllers;

import com.lesson22java.entities.Customer;
import com.lesson22java.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    @GetMapping("/create")
    public Customer create(@ModelAttribute Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping("/getAll")
    public Iterable<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/getOne/{id}")
    public Customer getOne(@PathVariable Long id) {
        return customerService.getOne(id);
    }

    @GetMapping("/edit")
    public Customer edit(@ModelAttribute Customer customer) {
        return customerService.edit(customer);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}
