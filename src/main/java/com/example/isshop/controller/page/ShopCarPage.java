package com.example.isshop.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopCarPage {

    @GetMapping("/page/user/shopCar")
    public String home() {
        return "shopCar";
    }
}
