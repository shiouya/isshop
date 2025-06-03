package com.example.isshop.service;

import com.example.isshop.entity.Product;
import com.example.isshop.entity.ShopCar;
import com.example.isshop.entity.Users;
import com.example.isshop.repository.ShopCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopCarService {

    @Autowired
    private ShopCarRepository shopCarRepository;

    public ShopCar saveShopCar(Users users, Product product) {
        ShopCar shopCar = shopCarRepository.findByUserAndProduct(users, product);
        if (shopCar != null) {
            shopCar.setQuantity(shopCar.getQuantity() + 1);
        } else {
            shopCar = new ShopCar();
            shopCar.setUser(users);
            shopCar.setProduct(product);
            shopCar.setQuantity(1);
        }
        return shopCarRepository.save(shopCar);
    }

    public List<ShopCar> getShopCarsByUser(Users users) {
        List<ShopCar> shopCars = shopCarRepository.findByUser(users);
        return shopCars;
    }

    public ShopCar updateShopCarQuantity(Integer shopCarid,String action) {
        Optional<ShopCar> shopC = shopCarRepository.findById(shopCarid);
        ShopCar shopCar = shopC.get();
        Integer quantity = shopCar.getQuantity();
        if(action.equals("add")) {
            shopCar.setQuantity(quantity + 1);
        }else if(action.equals("cut")) {
            shopCar.setQuantity(quantity - 1);
        }
        ShopCar save = shopCarRepository.save(shopCar);
        if(action.equals("delete")) {
            shopCarRepository.delete(shopCar);
            save.setQuantity(0);
        }
        return save;
    }

    public ShopCar getShopCarById(Integer shopCarid) {
        return shopCarRepository.findById(shopCarid).get();
    }

    public void deleteShopCarById(Integer shopCarid) {
        shopCarRepository.deleteById(shopCarid);
    }

}
