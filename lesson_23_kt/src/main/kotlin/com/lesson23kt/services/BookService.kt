package com.lesson23kt.services

import com.lesson23kt.entities.Book
import com.lesson23kt.repositories.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

//1. Добавить на html странице навигацию по страницам.
// Находясь на первой странице кнопка prev должна быть неактивна, то же самое и для кнопки next.
// При переходе между страницами фильтр не должен сбрасываться
// [ Панель навигации можно брать отсюда: https://getbootstrap.com/docs/4.5/components/pagination/ ];
//2. Добавьте форму для фильтра, после фильтрации форма не должна сбрасываться;
// Сделайте фильтр по одному или нескольким жанрам.
// Жанры выбираются как checkbox'ы. Упрощение: книга имеет только один жанр

@Service
class BookService (val bookRepository: BookRepository) {
    fun findAll(spec: Specification<Book>) = bookRepository.findAll(spec)

    fun findById(id: Long) = bookRepository.findById(id)

    fun deleteById(id: Long) = bookRepository.deleteById(id)

    fun save(book: Book) = bookRepository.save(book)

    fun findAll(spec: Specification<Book>, pageNumber : Int, pageSize: Int) =
        bookRepository.findAll(spec, PageRequest.of(pageNumber, pageSize))

}