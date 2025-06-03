package com.example.isshop.controller;

import com.example.isshop.dao.ProductsRoles;
import com.example.isshop.entity.Product;
import com.example.isshop.entity.Users;
import com.example.isshop.service.ProductService;
import com.example.isshop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Product Controller", description = "產品增刪改查相關的 API")
@RestController
public class ProductController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> getProductAll() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String role="ROLE_Guest";
        if (username != "anonymousUser") {
            Users user = userService.findByName(username);
            role=user.getRoles().get(0).getRoleName();
        }

        ArrayList<Product> products = new ArrayList<>();
        for (Product product : productService.findAll(username)) {
            Product p = new Product();
            p.setProductId(product.getProductId());
            p.setProductName(product.getProductName());
            p.setPhoto(product.getPhoto());
            p.setPrice(product.getPrice());
            products.add(p);
        }
        ProductsRoles productsRoles = new ProductsRoles();
        productsRoles.setRole(role);
        productsRoles.setProducts(products);
        return ResponseEntity.ok(productsRoles);
    }


    @PutMapping("/product/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id
            ,@RequestParam("file") MultipartFile file
            ,@RequestParam("productName") String productName
            ,@RequestParam("productPrice") Integer productPrice) throws IOException {
        Product product = productService.findById(id);
        product.setProductName(productName);
        product.setPhoto(file.getBytes());
        product.setPrice(productPrice);

        productService.create(product);
        return ResponseEntity.ok("修改成功");
    }

    @Operation(summary = "新增一個產品", description = "傳送資料新增產品")
    @PostMapping("/product/create")
    public ResponseEntity<?> createProduct(@RequestParam("file") MultipartFile file
            ,@RequestParam("productName") String productName
            ,@RequestParam("productPrice") Integer productPrice) throws IOException {
        Product product = new Product();
        product.setProductName(productName);
        product.setPhoto(file.getBytes());
        product.setPrice(productPrice);

        productService.create(product);
        return ResponseEntity.ok("新增成功");
    }

    @GetMapping("/product/photo/{id}")
    public ResponseEntity<byte[]> getProductPhoto(@PathVariable Integer id) {
        Product product = productService.findById(id);
        byte[] imageBytes = product.getPhoto(); // 假設是 byte[] 型態
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 根據實際格式改成 PNG/JPEG
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);

    }


}
