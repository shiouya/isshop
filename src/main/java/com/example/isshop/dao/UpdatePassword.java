package com.example.isshop.dao;

import lombok.Data;

@Data
public class UpdatePassword {

    private String oldpassword;
    private String newpassword;
}
