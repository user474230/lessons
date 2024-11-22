package com.lesson22.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "customer")
data class Customer (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,
    @Column(name = "fio")
    var fio: String? = null,
    @Column(name = "age")
    var age: Int? = null,
    //@Type(type = "jsonb")
    @Column(name = "test", columnDefinition = "jsonb")
    var test: MyType? = null
)

data class MyType (
    var long: Long? = null,
    var string: String? = null,
    var string2: String? = null
)
