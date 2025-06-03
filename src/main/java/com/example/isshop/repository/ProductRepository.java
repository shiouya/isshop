package com.example.isshop.repository;

import com.example.isshop.entity.Order;
import com.example.isshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Integer> {
    @Query(value = "SELECT TOP 3 * FROM product ORDER BY NEWID()", nativeQuery = true)
    List<Product> findRandom3Products();

    Product findByProductId(Integer productId);
}
