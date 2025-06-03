package com.example.isshop.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginPage {

    @GetMapping("/page/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/page/user/create")
    public String userCreate() {
        return "userCreate";
    }

    @GetMapping("/page/user/update")
    public String userUpdate() {
        return "userUpdate";
    }

}
