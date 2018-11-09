package com.shop.dev.back_respository;

import com.shop.dev.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName ItemRepository
 * @Author 刘树青
 * @Date 2018/11/8 14:07
 * @Version 1.0
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

}
