package com.shop.dev.service;

import com.shop.dev.result_wrapper.ShopResult;
import com.shop.dev.entity.Item;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ItemService
 * @Author 刘树青
 * @Date 2018/11/8 14:47
 * @Version 1.0
 */
public interface ItemService {

    Map findItems(int page, int rows);

    // 上下架及其删除
    ShopResult updateItemStatus(List<Long> itemIds, String method);

    // 添加商品
    ShopResult addItem(Item item, String itemDesc, String itemParams);

    // 根据id查询商品描述
    ShopResult getItemDesc(Long id);

    // 更新商品
    ShopResult updateItem(Item item, String itemDesc, String itemParams);

}
