package com.shop.dev.service;

import com.shop.dev.controller.param.ItemParam;

import java.util.List;

public interface ICartService {

    void addItem(ItemParam itemParam);

    List<?> showItemInformation(Long userId);

    void removeItem(Long itemId);

    void updateItemQuantity(ItemParam itemParam);

}
