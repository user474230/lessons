package com.lesson23kt.repositories.specifications

import com.lesson23kt.entities.Book
import org.springframework.data.jpa.domain.Specification

class BookSpecifications {
    companion object {
        fun titleLike(title: String) =
            Specification<Book> { root, query, builder ->
                builder.like(root.get("title"), "%$title%")
            }

        fun priceGreater(minPrice: Int) =
            Specification<Book> { root, query, builder ->
                builder.greaterThan(root.get("price"), minPrice)
            }

        fun priceLess(maxPrice: Int) =
            Specification<Book> { root, query, builder ->
                builder.lessThan(root.get("price"), maxPrice)
            }

        fun genre(genre: String) =
            Specification<Book> { root, query, builder ->
                builder.equal(root.get<String>("genre"), genre)
            }
    }
}