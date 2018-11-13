package com.shop.dev.controller;

import com.shop.dev.controller.response_web.ItemResult;
import com.shop.dev.entity.ItemParam;
import com.shop.dev.service.ItemParamService;
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
 * @ClassName ItemParamController
 * @Author 刘树青
 * @Date 2018/11/9 13:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/item/param")
public class ItemParamController {

    @Resource
    private ItemParamService itemParamService;

    @GetMapping("/list")
    public Map findItemParams(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows) {
        // 遇到的坑:在PageRequest类中,计算的页码是从0开始的,而前端最小的页面则是1
        Page<ItemParam> itemParams = this.itemParamService.findItemParams(page - 1, rows);
        List<ItemParam> itemParamList = this.itemParamService.findItemParams();
        Map map = new HashMap();
        map.put("total", itemParamList.size());
        map.put("rows", itemParams.getContent());
        return map;
    }

    @RequestMapping("/delete")
    public ItemResult deleteItemParam(@RequestParam("ids") List<Long> ids) {
        return itemParamService.deleteItemParam(ids);
    }
}
