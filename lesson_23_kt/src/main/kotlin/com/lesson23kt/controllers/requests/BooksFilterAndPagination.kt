package com.lesson23kt.controllers.requests

data class BooksFilterAndPagination(
    var filter: BookFilter?,
    var pagination: BookPagination?,
)

data class BookPagination(
    var pageNumber: Int?,
    var pageSize: Int?
)

data class BookFilter(
    var title: String?,
    var priceFrom: Int?,
    var priceTo: Int?,
    var genre: String?,
)