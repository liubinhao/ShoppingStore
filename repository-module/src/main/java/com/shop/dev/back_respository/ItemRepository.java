package com.shop.dev.back_respository;

import com.shop.dev.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName ItemRepository
 * @Author 刘树青
 * @Date 2018/11/8 14:07
 * @Version 1.0
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Modifying
    @Query("update Item set status= :status where id in :ids")
    int updateByItemIds(@Param("status") byte status, @Param("ids") List<Long> ids);


}
