package com.shop.dev.service;

import com.shop.dev.entity.ItemDesc;

/**
 * @ClassName ItemDescService
 * @Author 刘树青
 * @Date 2018/11/12 15:39
 * @Version 1.0
 */
public interface ItemDescService {
    ItemDesc findByitemId(long itemId);

//    ItemDesc updateItemDesc(ItemDesc itemDesc);
}
