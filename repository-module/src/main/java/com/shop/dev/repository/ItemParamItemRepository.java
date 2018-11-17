package com.shop.dev.repository;

import com.shop.dev.entity.ItemParamItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName ItemParamItemRepository
 * @Author 刘树青
 * @Date 2018/11/16 19:38
 * @Version 1.0
 */
public interface ItemParamItemRepository extends JpaRepository<ItemParamItem, Long> {

    ItemParamItem findByItemId(Long itemId);
}
