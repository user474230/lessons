package com.lesson23kt.controllers

import com.lesson23kt.controllers.requests.BooksFilterAndPagination
import com.lesson23kt.controllers.requests.GenreObject
import com.lesson23kt.entities.Book
import com.lesson23kt.enumerations.Genre
import com.lesson23kt.repositories.specifications.BookSpecifications
import com.lesson23kt.services.BookService
import com.lesson23kt.utils.BookSpecificationHelper
import org.springframework.data.domain.Page
import org.springframework.data.jpa.domain.Specification
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/books")
class BookController (
    val bookService: BookService
){
    @GetMapping()
    fun all() = bookService.findAll(Specification.where<Book>(null))

    @PostMapping("/withFilter")
    fun allWithFilter(@RequestBody booksFilterAndPagination: BooksFilterAndPagination) : Page<Book> {
        println(booksFilterAndPagination)
        val pageNumber = booksFilterAndPagination.pagination?.pageNumber ?: 1
        val pageSize = booksFilterAndPagination.pagination?.pageSize ?: 5
        return bookService.findAll(
            BookSpecificationHelper(booksFilterAndPagination.filter).spec,
            pageNumber - 1,
            pageSize
            )
    }

    @GetMapping("/genres")
    fun allGenres() = Genre.entries.map { it -> GenreObject(name = it.genreName, value = it.name) }


    @GetMapping("/{id}")
    fun findById(@PathVariable id : Long) = bookService.findById(id)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id : Long) = bookService.deleteById(id)

    @PutMapping()
    fun edit(@RequestBody book: Book) = bookService.save(book)

    @PostMapping()
    fun save(@RequestBody book: Book) = bookService.save(book)
}