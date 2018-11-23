package com.shop.dev.repository;

import com.shop.dev.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * if the class no bug ,the author is 高帅.
 * Else, emm  ... I don't know the author.
 * Create at: 2018/11/7下午 5:09
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByUserId(int userId, Pageable pageable);

    int countFindByUserId(Integer userId);

    //    List<Order> findAllByCreateTimeBetween(Long passTime, Long createTime);
    List<Order> findAllByStatus(int status);

//    @Query(value = "select * from(select distinct tor.order_id ,tor.user_id from tb_order tor\n" +
//            "        left join tb_order_item toi on toi.order_id = tor.order_id\n" +
//            "        left join tb_item ti on ti.item_id = toi.item_id\n" +
//            "        left join tb_order_shipping tos on tos.order_shipping_id = tor.order_shipping_id\n" +
//            " where ti.item_id\n" +
//            "    or tor.order_id\n" +
//            "    or ti.title like ?)as tb1 where tb1.user_id=1",nativeQuery = true)
//    List<Order> findAllByItemIdOrTitleOrOrderId(String info);
//
//    List<Order> findByOrderIdIn(List<Integer> orderIds);
}

