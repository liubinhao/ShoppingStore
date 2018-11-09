package com.shop.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class StaticSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaticSpringApplication.class);
    }
}
