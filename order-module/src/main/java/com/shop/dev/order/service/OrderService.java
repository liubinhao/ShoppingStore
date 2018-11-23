package com.shop.dev.order.service;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.entity.Order;
import com.shop.dev.repository.OrderItemRepository;
import com.shop.dev.repository.OrderRepository;
import com.shop.dev.repository.OrderShippingRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * if the class no bug ,the author is 高帅.
 * Else, emm  ... I don't know the author.
 * Create at: 2018/11/7下午 2:13
 */
@Service
@Transactional
public class OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private OrderItemRepository orderItemRepository;

    @Resource
    private OrderShippingRepository orderShippingRepository;


    @Transactional(readOnly = true)
    public ResultWrapper findAll() {
        List<Order> orderList = orderRepository.findAll();

        return ResultWrapper.success(orderList);
    }

    //    根据userId查询他的所有订单
    @Transactional(readOnly = true)
    public ResultWrapper findOrderByUserId(Integer userId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.ASC, "orderId");
        Pageable pageable = PageRequest.of(page, size, sort);
        List<Order> byUserId = orderRepository.findAllByUserId(userId, pageable);
        if (byUserId != null) {
            int totalCount = orderRepository.countFindByUserId(userId);
            com.shop.dev.commons.Pageable paginfo=new com.shop.dev.commons.Pageable(page,size,totalCount,byUserId);
            return ResultWrapper.success(paginfo);
        }
        return ResultWrapper.error(401, "用户id错误");
    }

    //      根据orderid查询订单(bug:能查到其他人的订单)
    @Transactional(readOnly = true)
    public ResultWrapper findOrderByOrderId(Integer orderId) {
        Optional<Order> byId = orderRepository.findById(orderId);
        if (byId.isPresent()) {
            List<Order> list = new ArrayList();
            list.add(byId.get());
            Map<String, Object> map = new HashMap<>();
            map.put("items", list);
            return ResultWrapper.success(map);
        }
        return ResultWrapper.error(401, "订单号错误");
    }


    //添加订单
    @Transactional
    public ResultWrapper saveOrder(Order order) {
        order.setCreateTime(new Date());
        Order save = orderRepository.save(order);
        return ResultWrapper.success(save);
    }

    //    修改订单状态
    @Transactional
    public ResultWrapper changeOrderStatus(Order status) {

        status.setUpdateTime(new Date());
        status.setStatus(2);
        Order save = orderRepository.save(status);
        return ResultWrapper.success(save);
    }

    //    删除订单

    public ResultWrapper deleteOrder(Integer orderId) {
        try {
            orderItemRepository.deleteByOrderId(orderId);
            orderShippingRepository.deleteByOrderId(orderId);
            orderRepository.deleteById(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultWrapper.success("ok");
    }


    //
//    @Transactional
//    public ResultWrapper countFindByUserId(Integer userId) {
//        int count = orderRepository.countFindByUserId(userId);
//        return ResultWrapper.success(count);
//    }

    //      订单模糊查询
//    @Transactional
//    public ResultWrapper findByCreateAndStatus(String createTime, int status) throws ParseException {
//        long currentTime = System.currentTimeMillis();
//        long pastTime = 1;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        switch (createTime) {
//            case "最近三个月":
//                pastTime = currentTime - (1000 * 90 * 1440 * 60);
//                break;
//
//            case "今年内":
//                pastTime = sdf.parse("2018-01-01").getTime();
//                break;
//            case "2017年":
//                pastTime = sdf.parse("2017-01-01").getTime();
//                currentTime = sdf.parse("2017-12-31").getTime();
//                break;
//            case "2017年前":
//                currentTime = sdf.parse("2017-01-01").getTime();
//                break;
//        }
//        List<Order> orderList = orderRepository.findAllByCreateTimeBetween(pastTime, currentTime);
//        if (orderList != null && status != -1) {
//            List<Order> list = new ArrayList<>();
//            for (Order order : orderList) {
//                if (order.getStatus() == status) {
//                    list.add(order);
//                }
//                return ResultWrapper.success(list);
//            }
//            return ResultWrapper.success(orderList);
//        }
//
//        return ResultWrapper.error(421, "没有订单");
//    }

    //springMvc servlet 接参数
    //    根据商品编号或商品名称或订单编号进行模糊查询
//
//    public ResultWrapper findByItemIdOrTitleOrOrderId(String info) {
//        List<Order> orderList = orderRepository.findAllByItemIdOrTitleOrOrderId(info);
//        List<Integer> orderIds = orderList.stream().map(Order::getOrderId).collect(Collectors.toList());
//        List<Order> orders = orderRepository.findByOrderIdIn(orderIds);
//        return ResultWrapper.success(orders);
//    }

////      商品评价
//    @Transactional
//    public ResultWrapper addComment()
}
