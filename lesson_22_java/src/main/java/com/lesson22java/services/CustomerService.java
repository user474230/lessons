package com.lesson22java.services;

import com.lesson22java.entities.Customer;
import com.lesson22java.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Iterable<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getOne(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("No such customer"));
    }

    public Customer edit(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
