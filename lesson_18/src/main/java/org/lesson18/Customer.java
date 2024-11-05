package org.lesson18;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "fio")
    private String fio;

    @ManyToMany
    @JoinTable(
            name = "purchase_details",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @OneToMany

    public List<Product> getProducts() {
        return products;
    }

    public UUID getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Customer(String fio) {
        super();
        this.fio = fio;
    }

    public Customer() {
    }
}
