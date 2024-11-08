package lesson20kt.services

import lesson20kt.entities.Product
import lesson20kt.repositories.ProductRepository
import org.springframework.stereotype.Component

@Component
class ProductService(val prodectRepository: ProductRepository) {

    fun save(product: Product) = prodectRepository.save(product)
    fun load(id: Long): Product = prodectRepository.load(id)
    fun loadAll(): List<Product> = prodectRepository.loadAll()
}