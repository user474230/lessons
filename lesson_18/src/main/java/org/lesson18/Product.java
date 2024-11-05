package org.lesson18;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private BigDecimal price;

    public UUID getId() {
        return id;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    @ManyToMany
    @JoinTable(
            name = "purchase_details",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )

    private List<Customer> customers;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product(String title, BigDecimal price) {
        super();
        this.title = title;
        this.price = price;
    }

    public Product() {
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
