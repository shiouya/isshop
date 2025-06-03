package com.example.isshop.service;

//import com.example.isshop.entity.User;
import com.example.isshop.entity.Users;
import com.example.isshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Service
public class UserDetailServiceImpl implements UserDetailsService{

//    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUserName(username);

        // User 是你實作的 Entity，要轉換成 UserDetails
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                        .collect(Collectors.toList())
        );




//        Users users = userRepository.findByUserName(username);
//        if (users == null) {
//            throw new UsernameNotFoundException("Can't find member: " + username);
//        }
//
//        List<SimpleGrantedAuthority> authorities = users.getRoles()
//                .stream()
//                .map(auth -> new SimpleGrantedAuthority(auth.getRoleName()))
//                .toList();
//
//        return User
//                .withUsername(username)
//                .password(users.getPassword())
//                .authorities(authorities)
//                .build();

    }
}
