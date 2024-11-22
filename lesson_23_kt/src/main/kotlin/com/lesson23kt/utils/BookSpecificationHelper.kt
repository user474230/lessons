package com.lesson23kt.utils

import com.lesson23kt.controllers.requests.BookFilter
import com.lesson23kt.entities.Book
import com.lesson23kt.repositories.specifications.BookSpecifications
import org.springframework.data.jpa.domain.Specification

class BookSpecificationHelper (
    params: BookFilter?
) {
    var spec: Specification<Book>
    init {
        spec = Specification.where<Book>(null)
        params?.title?.let {
            spec = spec.and(BookSpecifications.titleLike(it))
        }
        params?.genre.takeIf { !it.isNullOrEmpty()}?.let {
            spec = spec.and(BookSpecifications.genre(it))
        }
        params?.priceFrom?.let {
            spec = spec.and(BookSpecifications.priceGreater(it))
        }
        params?.priceTo?.let {
            spec = spec.and(BookSpecifications.priceLess(it))
        }
    }

}