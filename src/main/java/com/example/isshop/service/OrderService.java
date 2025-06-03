package com.example.isshop.service;

import com.example.isshop.entity.Order;
import com.example.isshop.entity.OrderItems;
import com.example.isshop.entity.ShopCar;
import com.example.isshop.entity.Users;
import com.example.isshop.repository.OrderItemsRepository;
import com.example.isshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ShopCarService shopCarService;

    public Order createOrders(Users user,List<Integer> shopCarId) {
        LocalDateTime now = LocalDateTime.now();
        Order order = new Order();
        order.setOrderDate(now);
        order.setUser(user);
        order.setStatus("處理中");
        order = orderRepository.save(order);

        Integer total = 0;

        for (Integer orderId : shopCarId) {
            ShopCar shopCar = shopCarService.getShopCarById(orderId);
            OrderItems orderItems = new OrderItems();
            orderItems.setOrder(order);
            orderItems.setQuantity(shopCar.getQuantity());
            orderItems.setProduct(shopCar.getProduct());
            int price=shopCar.getQuantity()*shopCar.getProduct().getPrice();
            orderItems.setPrice(price);
            total+=price;
            orderItemsRepository.save(orderItems);
            shopCarService.deleteShopCarById(orderId);
        }
        order.setTotal(total);
        order = orderRepository.save(order);
        return order;
    }

    public List<Order> findOrders(Users user) {
        List<Order> orders = orderRepository.findByUser(user);
        return orders;
    }

}
