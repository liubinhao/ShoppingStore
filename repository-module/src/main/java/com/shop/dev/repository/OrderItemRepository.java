package com.shop.dev.repository;

import com.shop.dev.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * if the class no bug ,the author is 高帅.
 * Else, emm  ... I don't know the author.
 * Create at: 2018/11/12上午 11:42
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    @Modifying
    @Query(value = "delete from tb_order_item where order_id = :orderId", nativeQuery = true)
    int deleteByOrderId(@Param("orderId") Integer orderId);
}
