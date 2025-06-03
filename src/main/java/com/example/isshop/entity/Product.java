package com.example.isshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String productName;

    private byte[] photo;

    private Integer price;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<ShopCar> shopcar;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderItems> orderItems;


}
