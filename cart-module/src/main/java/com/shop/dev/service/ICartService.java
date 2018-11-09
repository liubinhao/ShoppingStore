package com.shop.dev.service;

import com.shop.dev.controller.param.ItemParam;
import com.shop.dev.entity.Item;

import java.util.List;
import java.util.Map;

public interface ICartService {

    ItemParam addItem(ItemParam itemParam);

    List<?> showItemInformation(Long userId);

}
