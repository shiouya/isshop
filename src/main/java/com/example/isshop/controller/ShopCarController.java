package com.example.isshop.controller;

import com.example.isshop.dao.ShopCarsResponse;
import com.example.isshop.entity.Product;
import com.example.isshop.entity.ShopCar;
import com.example.isshop.entity.Users;
import com.example.isshop.service.ProductService;
import com.example.isshop.service.ShopCarService;
import com.example.isshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class ShopCarController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShopCarService shopCarService;

    @PostMapping("/shopcar/create/{id}")
    public ResponseEntity<?> create(@PathVariable Integer id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(1);
        Users user = userService.findByName(username);
        Product product = productService.findById(id);
        shopCarService.saveShopCar(user,product);
        return ResponseEntity.ok("加入購物車成功");
    }

    @GetMapping("/shopCar/user")
    public ResponseEntity<?> getShopCarByUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userService.findByName(username);
        List<ShopCar> shopCars = shopCarService.getShopCarsByUser(user);
        List<ShopCarsResponse> shopCarsResponse = new LinkedList<>();
        for (ShopCar shopCar : shopCars) {
            ShopCarsResponse shopCarsR = new ShopCarsResponse();
            shopCarsR.setQuantity(shopCar.getQuantity());
            shopCarsR.setProductName(shopCar.getProduct().getProductName());
            shopCarsR.setProductId(shopCar.getProduct().getProductId());
            shopCarsR.setProductPrice(shopCar.getProduct().getPrice());
            shopCarsR.setShopCarId(shopCar.getShopcarId());
            shopCarsResponse.add(shopCarsR);
        }
        return ResponseEntity.ok(shopCarsResponse);
    }

    @PutMapping("/shopCar/user/quantity/{id}/{action}")
    public ResponseEntity<?> updateShopCarQuantity(@PathVariable Integer id, @PathVariable String action) {
        System.out.println(id+" "+action);
        ShopCar shopCar = shopCarService.updateShopCarQuantity(id, action);
        return ResponseEntity.ok(shopCar.getQuantity());
    }

}
