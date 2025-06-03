package com.example.isshop.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderPage {

    @GetMapping("/page/user/order")
    public String home() {
        return "myorder";
    }
}
