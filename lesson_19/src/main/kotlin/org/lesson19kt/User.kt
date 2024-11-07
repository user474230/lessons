package org.lesson19kt

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "my_user")
data class User(
    @Id
    @Column(name = "id")
    var id: Long? = null,
    @Column(name = "fio")
    var fio: String? = null,
)