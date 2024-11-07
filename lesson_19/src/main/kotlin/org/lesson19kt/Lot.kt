package org.lesson19kt

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.persistence.Version

@Entity
@Table(name = "lot")
data class Lot(
    @Id
    @Column(name = "id")
    var id: Long? = null,
    @Column(name = "title")
    var title: String? = null,
    @Column(name = "price")
    var price: Long? = null,
    @Version
    var version: Long? = null,

    @OneToOne
    @JoinColumn(name = "user_id")
    var user: User? = null
)