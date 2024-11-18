package com.lesson22.repositories

import com.lesson22.entities.Product
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<Product, Long>{
}