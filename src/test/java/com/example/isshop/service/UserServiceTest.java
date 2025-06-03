package com.example.isshop.service;

import com.example.isshop.entity.Product;
import com.example.isshop.entity.Users;
import com.example.isshop.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {

    @Autowired // Spring 自動注入 MockMvc 實例，用於發送模擬的 HTTP 請求。
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test // 標註這是一個測試方法，測試創建使用者時的輸入驗證。
    public void testLoginUserValidationError() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType("application/json")
                        .content("{ \"userName\": \"aa\" }"))//沒有輸入密碼
                .andExpect(status().isBadRequest())
                .andExpect(content().string("請正確輸入帳號密碼"));
    }

    @Test
    void login() {
        Users login = userService.login("aa", "1234");
        assertEquals(login.getUsername(), "aa");
    }
}