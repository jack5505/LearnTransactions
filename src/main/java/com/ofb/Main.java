package com.ofb;

import com.ofb.config.Config;
import com.ofb.repositories.ProductRepository;
import com.ofb.services.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        try(var i = new AnnotationConfigApplicationContext(Config.class, ProductRepository.class,ProductService.class)){
            ProductService productService  = i.getBean(ProductService.class);
            productService.addProducts();
        }
    }
}
