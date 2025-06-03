package com.example.isshop.controller;

import com.example.isshop.entity.Order;
import com.example.isshop.entity.Users;
import com.example.isshop.service.OrderService;
import com.example.isshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/create")
    public ResponseEntity<?> addOrder(@RequestBody List<Integer> shopCar) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(12);
        Users user = userService.findByName(username);
        orderService.createOrders(user,shopCar);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getOrders() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userService.findByName(username);
        List<Order> orders = orderService.findOrders(user);
        return ResponseEntity.ok(orders);
    }
}
