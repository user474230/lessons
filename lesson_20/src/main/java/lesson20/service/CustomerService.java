package lesson20.service;

import lesson20.entities.Customer;
import lesson20.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public void create(Customer customer) {
        customerRepository.create(customer);
    }

    public Customer getById(Long id) {
        return customerRepository.getById(id);
    }

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }
}
