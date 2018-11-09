package com.shop.dev.service;

import com.shop.dev.entity.Item;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ClassName ItemService
 * @Author 刘树青
 * @Date 2018/11/8 14:47
 * @Version 1.0
 */
public interface ItemService {
    Page<Item> findItems(int page, int rows);

    List<Item> findItems();
}
