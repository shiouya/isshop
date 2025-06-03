package com.example.isshop.controller.page;

import com.example.isshop.entity.Product;
import com.example.isshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductPage {

    @Autowired
    private ProductService productService;

    @GetMapping("/page/product/{id}")
    public String home(@PathVariable int id, Model model) {
        Product product = new Product();
        Boolean create=false;
        if(id==0){
            product.setPrice(0);
            product.setProductName("");
            product.setProductId(0);
            create=true;
        }else{
            product = productService.findById(id);
        }
        model.addAttribute("create",create);
        model.addAttribute("product", product);
        return "product";
    }
}
