package com.shop.dev.order.controller;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.entity.Order;
import com.shop.dev.order.service.OrderService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    // 查询全部订单
    @RequestMapping("/findAllOrder")

    public ResultWrapper findAllOrder() {
        return orderService.findAll();
    }

    // 查询某个人的订单
    @RequestMapping("/findOrderByUserId/{userId}/{page}/{size}")
    public ResultWrapper findOrderByUserId(
            @PathVariable Integer userId, @PathVariable Integer page, @PathVariable Integer size) {
//        System.out.println(userId, size, page);
        int a = 10;
        return orderService.findOrderByUserId(userId, page, size);
    }

    // 根据订单号查询订单
    @RequestMapping("/findOrderByOrderId/{orderId}")
    public ResultWrapper findOrderByOrderId(
            @PathVariable Integer orderId) {

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

    //    删除订单
    @RequestMapping("/deleteOrder/{orderId}")
    public ResultWrapper deleteOrder(@PathVariable Integer orderId) {
        return orderService.deleteOrder(orderId);
    }




    //  查询某个人 一共有多少条订单
//    @RequestMapping("/findCountByUserId")
//    public ResultWrapper countFindByUserId(Integer userId) {
//        return orderService.countFindByUserId(userId);
//    }


//    //  条件查询订单
//    @RequestMapping("/findByInfo/{info}")
//    public ResultWrapper findByInfo(String info) {
//        return orderSerzvice.findByItemIdOrTitleOrOrderId(info);
//    }

}
