package com.example.isshop.controller.page;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomePage {

    @GetMapping("/page/home")
    public String home() {
        return "home";
    }

}
