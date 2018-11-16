package com.shop.dev.controller;

import com.shop.dev.result_wrapper.ShopResult;
import com.shop.dev.service.ItemParamItemService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ItemParamItemController
 * @Author 刘树青
 * @Date 2018/11/16 19:44
 * @Version 1.0
 */
@RestController
public class ItemParamItemController {
    @Resource
    private ItemParamItemService itemParamItemService;

    @RequestMapping("/item/param/item/query/{itemId}")
    public ShopResult findIItemParamItem(@PathVariable long itemId) {
        return this.itemParamItemService.findIItemParamItem(itemId);
    }
}
