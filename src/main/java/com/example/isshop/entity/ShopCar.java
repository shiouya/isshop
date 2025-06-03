package com.example.isshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.swing.*;

@Data
@Entity
@Table(name = "shopcar")
public class ShopCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shopcarId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
}
