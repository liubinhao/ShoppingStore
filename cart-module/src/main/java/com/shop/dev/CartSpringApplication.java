package com.shop.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CartSpringApplication {
    public static void main(String[] args) {
    SpringApplication.run(CartSpringApplication.class);
    }
}
