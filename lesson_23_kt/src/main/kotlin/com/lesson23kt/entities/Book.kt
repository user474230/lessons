package com.lesson23kt.entities

import com.lesson23kt.enumerations.Genre
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "book")
data class Book (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,
    @Column(name = "title")
    var title: String? = null,
    @Column(name = "price")
    var price: Int? = null,
    @Column(name = "genre")
    @Enumerated(value = EnumType.STRING)
    var genre: Genre? = null,
)