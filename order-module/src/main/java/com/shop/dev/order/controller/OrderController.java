package com.shop.dev.order.controller;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.entity.Order;
import com.shop.dev.order.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * if the class no bug ,the author is 高帅.
 * Else, emm  ... I don't know the author.
 * Create at: 2018/11/7下午 2:12
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    //     查询全部订单
    @RequestMapping("/findAllOrder")
    public ResultWrapper findAllOrder() {

        return orderService.findAll();
    }

    // 查询某个人的订单
    @RequestMapping("/findOrderByUserId")
    public ResultWrapper findOrderByUserId(
            @RequestParam(value = "userId", defaultValue = "") Integer userId) {
        System.out.println(userId);
        return orderService.findOrderByUserId(userId);
    }

    // 根据订单号查询订单
    @RequestMapping("/findOrderByOrderId")
    public ResultWrapper findOrderByOrderId(
            @RequestParam(value = "orderId", defaultValue = "") Integer orderId) {
        return orderService.findOrderByOrderId(orderId);
    }

    //  修改订单
    @RequestMapping("/changOrderStatus")
    public ResultWrapper changOrderStatus(Order statusId) {
        return orderService.changeOrderStatus(statusId);
    }

    //添加订单
    @RequestMapping("/addOrder")
    public ResultWrapper addOrder(Order order) {
        return orderService.saveOrder(order);
    }
}
