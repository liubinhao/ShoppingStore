package com.shop.dev.controller;

import com.shop.dev.result_wrapper.ShopResult;
import com.shop.dev.entity.Item;
import com.shop.dev.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ItemController
 * @Author 刘树青
 * @Date 2018/11/8 14:50
 * @Version 1.0
 */
@RestController
public class ItemController {
    @Resource
    private ItemService itemService;

    // 查询商品信息
    @GetMapping("/item/list")
    public Map findItems(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows) {
        return this.itemService.findItems(page, rows);
    }

    /**
     * @Author 刘树青 上下架及其删除
     * @Date 2018/11/12 15:50
     * @param: [ids, method]
     * return: com.shop.dev.result_wrapper.ShopResult
     */
    @RequestMapping("/item/{method}")
    public ShopResult updateItemStatus(@RequestParam(value = "ids") List<Long> ids, @PathVariable String method) {
        return this.itemService.updateItemStatus(ids, method);
    }


    // 添加商品
    @RequestMapping("/item/save")
    public ShopResult saveItem(Item item, String desc, String itemParams) {
        return itemService.addItem(item, desc, itemParams);
    }


    // 根据id获取商品描述
    @RequestMapping(value = "/item/desc/{id}")
    public ShopResult getItemDesc(@PathVariable("id") Long id) {
        return this.itemService.getItemDesc(id);
    }


    // 更新商品信息
    @RequestMapping(value = "/item/update")
    public ShopResult updateItem(Item item, String desc, String itemParams) {
        return this.itemService.updateItem(item, desc, itemParams);
    }


}
