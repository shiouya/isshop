package com.example.isshop.dao;

import com.example.isshop.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductsRoles {

    private String role;
    private List<Product> products;
}
