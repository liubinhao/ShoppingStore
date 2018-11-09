package com.shop.dev.controller;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.entity.Item;
import com.shop.dev.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/item")
public class ItemController {
    @Resource
    private ItemService itemService;

    @GetMapping("/list")
    public Map findItems(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows) {
        // 获取到分页数据
        Page<Item> items = this.itemService.findItems(page - 1, rows);
        // 拿到数据总数
        List<Item> itemList = this.itemService.findItems();
        Integer total = itemList.size();
        // 返回给前端所需数据
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", items.getContent());
        return map;
    }
}
