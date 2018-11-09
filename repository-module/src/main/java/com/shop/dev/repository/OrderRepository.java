package com.shop.dev.repository;

import com.shop.dev.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * if the class no bug ,the author is 高帅.
 * Else, emm  ... I don't know the author.
 * Create at: 2018/11/7下午 5:09
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByUserId(int userId);
}
