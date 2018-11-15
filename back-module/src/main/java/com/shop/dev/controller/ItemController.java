package com.shop.dev.controller;

import com.shop.dev.controller.response_web.ShopResult;
import com.shop.dev.entity.Item;
import com.shop.dev.service.ItemDescService;
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

    @Resource
    private ItemDescService itemDescService;

    @GetMapping("/item/list")
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

    /**
     * @Author 刘树青 上下架及其删除
     * @Date 2018/11/12 15:50
     * @param: [ids, method]
     * return: com.shop.dev.controller.response_web.ShopResult
     */
    @RequestMapping("/rest/item/{method}")
    public ShopResult updateItemStatus(@RequestParam(value = "ids") List<Long> ids, @PathVariable String method) {
        return this.itemService.updateItemStatus(ids, method);
    }

//    @RequestMapping("/rest/item/query/item/desc/{id}")
//    public ShopResult getItemDesc(@PathVariable("id") long id) {
//        ItemDesc itemdesc = this.itemDescService.findByitemId(id);
//        return new ShopResult(200, "ok", itemdesc);
//    }
//
//    @CacheEvict(value = "itemService", allEntries = true)
//    @Transactional
//    @RequestMapping("/rest/item/update")
//    public ShopResult updateItemWithDesc(Item item, String desc) {
//        Item item1 = this.itemService.updateItem(item);
//        ItemDesc itemDesc = new ItemDesc();
//        itemDesc.setItemId(item1.getId());
//        itemDesc.setItemDesc(desc);
//        ItemDesc itemDesc1 = this.itemDescService.updateItemDesc(itemDesc);
//        return new ShopResult(200, "ok", null);
//    }

}
