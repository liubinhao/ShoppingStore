package com.shop.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 创建人: 武奇
 * 创建事件: 2018/11/5
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableConfigServer
public class ConfigSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigSpringApplication.class, args);
    }
}
