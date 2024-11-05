package org.lesson18;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "purchase_details")
public class PurchaseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "count")
    private Long count;
    @Column(name = "customer_id")
    private UUID customerId;
    @Column(name = "product_id")
    private UUID productId;

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getProductId() {
        return productId;
    }

    public PurchaseDetails(UUID productId, UUID customerId, BigDecimal price, Long count) {
        this.customerId = customerId;
        this.productId = productId;
        this.price = price;
        this.count = count;
    }

    public PurchaseDetails() {
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getCount() {
        return count;
    }
}
