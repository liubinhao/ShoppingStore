package com.shop.dev.repository;

import com.shop.dev.entity.OrderShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * if the class no bug ,the author is 高帅.
 * Else, emm  ... I don't know the author.
 * Create at: 2018/11/12上午 11:43
 */
public interface OrderShippingRepository extends JpaRepository<OrderShipping, Integer> {
//    @Modifying
//    @Query(value = "delete from tb_order_shipping where order_shipping_id = :orderId", nativeQuery = true)
//    int deleteByOrderId(@Param("orderId") Integer orderId);

  List<OrderShipping> findAllByUserId(Integer userId);
}
