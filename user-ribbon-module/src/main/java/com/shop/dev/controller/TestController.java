package com.shop.dev.controller;

import com.shop.dev.commons.ResultWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/home")
    public ResultWrapper home(){
        return this.restTemplate.getForObject("http://cart-module/cart/showItem", ResultWrapper.class);
    }
}
