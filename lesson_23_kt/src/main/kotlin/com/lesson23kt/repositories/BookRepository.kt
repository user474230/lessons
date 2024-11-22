package com.lesson23kt.repositories

import com.lesson23kt.entities.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface BookRepository: JpaRepository<Book, Long>, JpaSpecificationExecutor<Book>