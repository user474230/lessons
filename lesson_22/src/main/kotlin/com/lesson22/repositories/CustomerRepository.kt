package com.lesson22.repositories

import com.lesson22.entities.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<Customer, Long> {
}