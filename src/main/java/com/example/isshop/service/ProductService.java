package com.example.isshop.service;

import com.example.isshop.entity.Product;
import com.example.isshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(String name) {
        if (name == "anonymousUser") {
            return productRepository.findRandom3Products();
        }else{
            return productRepository.findAll();
        }
    }

    public Product findById(int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

}
