package com.example.isshop.repository;

import com.example.isshop.entity.Order;
import com.example.isshop.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository  extends JpaRepository<Roles,Integer> {
    Roles findByRoleName(String name);
}
