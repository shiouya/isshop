package com.example.isshop.repository;

import com.example.isshop.entity.Product;
import com.example.isshop.entity.ShopCar;
import com.example.isshop.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopCarRepository  extends JpaRepository<ShopCar,Integer> {
    ShopCar findByUserAndProduct(Users user, Product product);
    List<ShopCar> findByUser(Users user);

    @Override
    Optional<ShopCar> findById(Integer integer);
}
