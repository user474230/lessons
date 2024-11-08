package lesson20kt.services

import lesson20kt.entities.Customer
import lesson20kt.repositories.CustomerRepository
import org.springframework.stereotype.Component

@Component
class CustomerService(val customerRepository: CustomerRepository) {

    fun save(customer: Customer) = customerRepository.save(customer)
    fun load(id: Long): Customer = customerRepository.load(id)
    fun loadAll(): List<Customer> = customerRepository.loadAll()
}