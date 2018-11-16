package com.shop.dev.controller;

import com.shop.dev.result_wrapper.ShopResult;
import com.shop.dev.service.ItemParamService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ItemParamController
 * @Author 刘树青
 * @Date 2018/11/9 13:05
 * @Version 1.0
 */
@RestController
public class ItemParamController {

    @Resource
    private ItemParamService itemParamService;

    // 查
    @GetMapping("/item/param/list")
    public Map findItemParams(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows) {
        return this.itemParamService.findItemParams(page, rows);
    }

    // 删
    @RequestMapping("/item/param/delete")
    public ShopResult deleteItemParam(@RequestParam("ids") List<Long> ids) {
        return itemParamService.deleteItemParam(ids);
    }


    // 通过商品类目id获取规格参数模板
    @RequestMapping("/item/param/query/itemcatid/{cid}")
    public ShopResult getItemCatByCid(@PathVariable Long cid) {
        return this.itemParamService.getItemCatByCid(cid);
    }


    // 插入规格参数模板
    @RequestMapping("/item/param/save/{cid}")
    public ShopResult insertItemParam(@PathVariable Long cid, String paramData) {
        return this.itemParamService.insertItemParam(cid, paramData);
    }

}
