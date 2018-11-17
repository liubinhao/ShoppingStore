package com.shop.dev.service;

import com.shop.dev.repository.ItemParamItemRepository;
import com.shop.dev.entity.ItemParamItem;
import com.shop.dev.result_wrapper.ShopResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName ItemParamItemServiceImpl
 * @Author 刘树青
 * @Date 2018/11/16 19:41
 * @Version 1.0
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
    @Resource
    private ItemParamItemRepository itemParamItemRepository;

    @Cacheable(value = "itemParamItemService")
    @Override
    public ShopResult findIItemParamItem(long itemId) {
        ItemParamItem itemParamItem = this.itemParamItemRepository.findByItemId(itemId);
        return new ShopResult(200, "ok", itemParamItem);
    }
}
