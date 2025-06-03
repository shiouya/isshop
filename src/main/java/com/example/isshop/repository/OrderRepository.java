package com.example.isshop.repository;

import com.example.isshop.entity.Order;
import com.example.isshop.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByUser(Users user);
}
