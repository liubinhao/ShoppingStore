package com.shop.dev.order.service;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.entity.OrderShipping;
import com.shop.dev.repository.OrderShippingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * if the class no bug ,the author is 高帅.
 * Else, emm  ... I don't know the author.
 * Create at: 2018/11/7上午 10:36
 */
@Service
@Transactional
public class ShippingServer {
    @Resource
    private OrderShippingRepository orderShippingRepository;


    //    物流查询收货人信息
    public ResultWrapper findByUserId(Integer userId) {
        List<OrderShipping> shippingList = orderShippingRepository.findAllByUserId(userId);
        return ResultWrapper.success(shippingList);
    }

    //创建物流
    public ResultWrapper save(OrderShipping orderShipping) {
        OrderShipping save = orderShippingRepository.save(orderShipping);
        return ResultWrapper.success(save);
    }
}
