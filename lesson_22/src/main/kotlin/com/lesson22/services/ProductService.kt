package com.lesson22.services

import com.lesson22.entities.Product
import com.lesson22.repositories.CustomerRepository
import com.lesson22.repositories.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService (val productRepository: ProductRepository){
    fun getAll() = productRepository.findAll()
    fun getById(id: Long) = productRepository.findById(id)
    fun create(product: Product) = productRepository.save(product)
    fun delete(id: Long) = productRepository.deleteById(id)
    fun edit(product: Product) = productRepository.save(product)
}