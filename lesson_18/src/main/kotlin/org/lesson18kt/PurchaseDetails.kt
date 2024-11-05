package org.lesson18kt

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "purchase_details")
data class PurchaseDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID? = null,
    @Column(name = "customer_id")
    var customerId: UUID? = null,
    @Column(name = "product_id")
    var productId: UUID? = null,

    @Column(name = "price")
    var price: BigDecimal? = null,
    @Column(name = "count")
    var count: Long? = null,
)