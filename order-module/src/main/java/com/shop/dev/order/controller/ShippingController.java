package com.shop.dev.order.controller;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.entity.OrderShipping;
import com.shop.dev.order.service.OrderService;
import com.shop.dev.order.service.ShippingServer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * if the class no bug ,the author is 高帅.
 * Else, emm  ... I don't know the author.
 * Create at: 2018/11/7上午 10:36
 */
@RestController
@RequestMapping("/shipping")
public class ShippingController {


    @Resource
    private ShippingServer shippingServer;

    //查询全部
    @RequestMapping("/findByUserId")
    private ResultWrapper findByUserId(Integer userId) {
        return shippingServer.findByUserId(userId);
    }

    @RequestMapping("save")
    private ResultWrapper save(OrderShipping orderShipping) {
        return shippingServer.save(orderShipping);
    }

}
