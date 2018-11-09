package com.shop.dev.controller;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.service.ItemCatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ItemCatController
 * @Author 刘树青
 * @Date 2018/11/8 19:39
 * @Version 1.0
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {
    @Resource
    private ItemCatService itemCatService;

    @GetMapping("/findItemCats")
    public ResultWrapper findItemCats(Long parentId) {
        return ResultWrapper.success(this.itemCatService.findByParentId(0L));
    }
}
