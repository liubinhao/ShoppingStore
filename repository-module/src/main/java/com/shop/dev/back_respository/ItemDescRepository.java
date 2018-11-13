package com.shop.dev.back_respository;

import com.shop.dev.entity.ItemDesc;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName ItemDescRepository
 * @Author 刘树青
 * @Date 2018/11/12 15:38
 * @Version 1.0
 */
public interface ItemDescRepository extends JpaRepository<ItemDesc, Long> {
}
