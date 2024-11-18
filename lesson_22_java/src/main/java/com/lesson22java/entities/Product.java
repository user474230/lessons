package com.lesson22java.entities;


import jakarta.persistence.*;
import lombok.Data;

//CREATE TABLE product (
//        id bigserial NOT NULL,
//        title text NULL,
//        price int4 NULL,
//        CONSTRAINT product_pk PRIMARY KEY (id)
//);
@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private Integer price;
}
