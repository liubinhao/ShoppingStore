package com.shop.dev.controller;

import com.shop.dev.controller.response_web.EasyUIResult;
import com.shop.dev.service.ItemCatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ItemCatController
 * @Author 刘树青
 * @Date 2018/11/8 19:39
 * @Version 1.0
 */
@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
    @Resource
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    public List<EasyUIResult> findItemCats(@RequestParam(value = "id", defaultValue = "0") long itemCatId) {
        return itemCatService.findItemCat(itemCatId);
    }
}
