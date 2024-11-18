package com.lesson22java.repositories;

import com.lesson22java.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository  extends CrudRepository<Customer, Long> {
}
