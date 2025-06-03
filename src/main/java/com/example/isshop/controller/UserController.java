package com.example.isshop.controller;

import com.example.isshop.config.Jwtutil;
import com.example.isshop.dao.UpdatePassword;
import com.example.isshop.entity.Users;
import com.example.isshop.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Jwtutil jwtutil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        if (user.getUsername()==null || user.getPassword()==null) {
            return ResponseEntity.badRequest().body("請正確輸入帳號密碼");
        }
        Users login = userService.login(user.getUsername(), user.getPassword());

        if (login!=null) {
            return ResponseEntity.ok(jwtutil.createToken(login.getUsername()));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("登入失敗:帳號或密碼錯誤");
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功新增"),
            @ApiResponse(responseCode = "401", description = "有人用了", content = @Content)
    })
    @PostMapping("/user/create")
    public ResponseEntity<String> create(@RequestBody Users user) {
        Users byName = userService.findByName(user.getUsername());
        if (byName!=null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("使用者名稱已被註冊");
        }
        userService.createUser(user);
        return ResponseEntity.ok("註冊成功");
    }
    
    @PutMapping("/user/update")
    public ResponseEntity<String> update(@RequestBody UpdatePassword pass) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users user = userService.findByName(username);
        String password = user.getPassword();
        if(!password.equals(pass.getOldpassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("舊密碼不同");
        }
        user.setPassword(pass.getNewpassword());
        Users users = userService.updateUser(user);
        if (users!=null) {
            return ResponseEntity.ok("修改成功");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("修改失敗");
        }

    }

}
