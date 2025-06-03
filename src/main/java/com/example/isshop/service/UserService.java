package com.example.isshop.service;

import com.example.isshop.entity.Roles;
import com.example.isshop.entity.Users;
import com.example.isshop.repository.RolesRepository;
import com.example.isshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;
    
    public Users login(String username, String password) {
        Users user = userRepository.findByUserName(username);
        if(user == null) {
            return null;
        }
        if(password.equals(user.getPassword())) {
            return user;
        }else{
            return null;
        }
    }

    public Users findByName(String name) {
        Users user = userRepository.findByUserName(name);
        return user;
    }

    public Users createUser(Users user) {
        Roles role = rolesRepository.findByRoleName("ROLE_USER");
        List<Roles> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);
        return userRepository.save(user);
    }

    public Users updateUser(Users user) {
        return userRepository.save(user);
    }

    @Transactional
    public Users loadUserWithRoles(String username) {
        Users user = userRepository.findByUserName(username);
        Hibernate.initialize(user.getRoles()); // 初始化 roles
        return user;
    }
    
}
