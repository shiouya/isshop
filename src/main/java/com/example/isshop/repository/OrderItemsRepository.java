package com.example.isshop.repository;

import com.example.isshop.entity.Order;
import com.example.isshop.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository  extends JpaRepository<OrderItems,Integer> {
}
