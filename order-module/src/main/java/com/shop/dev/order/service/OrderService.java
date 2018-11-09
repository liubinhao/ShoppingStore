package com.shop.dev.order.service;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.entity.Order;
import com.shop.dev.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * if the class no bug ,the author is 高帅.
 * Else, emm  ... I don't know the author.
 * Create at: 2018/11/7下午 2:13
 */
@Service
@Transactional
public class OrderService {

    @Resource
    private OrderRepository orderpository;


    @Transactional(readOnly = true)
    public ResultWrapper findAll() {
        List<Order> orderList = orderpository.findAll();

        return ResultWrapper.success(orderList);
    }

    //    根据userId查询他的所有订单
    @Transactional(readOnly = true)
    public ResultWrapper findOrderByUserId(Integer userId) {
        List<Order> byUserId = orderpository.findAllByUserId(userId);
        if (byUserId != null) {
            return ResultWrapper.success(byUserId);
        }
        return ResultWrapper.error(401, "用户id错误");
    }

    //      根据orderid查询订单(bug:能查到其他人的订单)
    @Transactional(readOnly = true)
    public ResultWrapper findOrderByOrderId(Integer orderId) {
        Optional<Order> byId = orderpository.findById(orderId);
        if (byId != null) {
            return ResultWrapper.success(byId);
        }
        return ResultWrapper.error(401, "订单号错误");
    }


    //添加订单
    @Transactional
    public ResultWrapper saveOrder(Order order) {
        order.setCreateTime(new Date());
        Order save = orderpository.save(order);
        return ResultWrapper.success(save);
    }

    //    修改订单状态
    @Transactional
    public ResultWrapper changeOrderStatus(Order status) {

        status.setUpdateTime(new Date());
        status.setStatus(2);
        Order save = orderpository.save(status);
        return ResultWrapper.success(save);
    }
}
