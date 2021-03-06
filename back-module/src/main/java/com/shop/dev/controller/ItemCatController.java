package com.shop.dev.controller;

import com.shop.dev.result_wrapper.EasyUITreeNode;
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
public class ItemCatController {
    @Resource
    private ItemCatService itemCatService;

    // 选择类目
    @RequestMapping("/item/cat/list")
    public List<EasyUITreeNode> findItemCats(@RequestParam(value = "id", defaultValue = "0") long itemCatId) {
        return itemCatService.findItemCat(itemCatId);
    }
}
