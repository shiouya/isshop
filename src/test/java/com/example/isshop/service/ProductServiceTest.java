package com.example.isshop.service;

import com.example.isshop.entity.Product;
import com.example.isshop.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;
    private Product product;

    @MockitoBean
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId(2);
        product.setProductName("手機");
        product.setPrice(30000);

        Mockito.when(productRepository.findByProductId(2)).thenReturn(product);
    }

    @AfterEach // 在每個測試方法執行之後執行，用於清理測試環境或重置狀態。
    public void tearDown() {
        Mockito.reset(productRepository); // 重置模擬對象，清除所有之前的行為模擬，確保不影響其他測試。
    }

    @Test
    void findById() {
        Product product1 = productRepository.findByProductId(2);
        System.out.println(product1.getProductName());
        assertEquals(product.getProductName(), product1.getProductName());
    }
}