package com.shop.dev.service;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.controller.param.ItemParam;

import java.util.List;

public interface ICartService {

    void addItem(ItemParam itemParam);

    List<?> showItemInformation();

    void removeItem(Long itemId);

    void updateItemQuantity(Long itemId, Integer buyNum);

    ResultWrapper batchDel(String[] keys);

    ResultWrapper findItemByItemId(Long itemId);

    ResultWrapper findShoppingList(String itemIds);


}
