package com.example.isshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private LocalDateTime orderDate;

    private Integer total;

    private String status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

//    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderItems> orderItems;

}
