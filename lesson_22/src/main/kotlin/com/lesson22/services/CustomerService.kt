package com.lesson22.services

import com.lesson22.entities.Customer
import com.lesson22.entities.Product
import com.lesson22.repositories.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService (val customerRepository: CustomerRepository){
    fun getAll() = customerRepository.findAll()
    fun getById(id: Long) = customerRepository.findById(id)
    fun create(customer: Customer) = customerRepository.save(customer)
    fun delete(id: Long) = customerRepository.deleteById(id)
    fun edit(customer: Customer) = customerRepository.save(customer)
}