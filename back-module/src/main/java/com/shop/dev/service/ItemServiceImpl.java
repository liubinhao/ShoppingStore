package com.shop.dev.service;

import com.shop.dev.repository.ItemDescRepository;
import com.shop.dev.repository.ItemParamItemRepository;
import com.shop.dev.repository.ItemRepository;
import com.shop.dev.entity.ItemParamItem;
import com.shop.dev.result_wrapper.ShopResult;
import com.shop.dev.entity.Item;
import com.shop.dev.entity.ItemDesc;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ItemServiceImpl
 * @Author 刘树青
 * @Date 2018/11/8 14:47
 * @Version 1.0
 */

@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    private ItemRepository itemRepository;

    @Resource
    private ItemDescRepository itemDescRepository;

    @Resource
    private ItemParamItemRepository itemParamItemRepository;

    @Cacheable(value = "itemService")
    @Override
    public Map findItems(int page, int rows) {
        // 获取到分页数据
        PageRequest pageable = PageRequest.of(page - 1, rows);
        Page<Item> items = this.itemRepository.findAll(pageable);
        // 拿到数据总数
        long count = this.itemRepository.findByCount();

        // 返回给前端所需数据
        Map map = new HashMap();
        map.put("total", count);
        map.put("rows", items.getContent());
        return map;
    }

    /**
     * @Author 刘树青 上下架及其删除
     * @Date 2018/11/12 15:00
     * @param: [itemIds, method]
     * return: com.shop.dev.result_wrapper.ShopResult
     */
    @Transactional
    @CacheEvict(value = "itemService", allEntries = true)
    @Override
    public ShopResult updateItemStatus(List<Long> itemIds, String method) {
        if (method.equals("reshelf")) {
            // 正常，更新status=3即可
            this.itemRepository.updateByItemIds((byte) 1, itemIds);
        } else if (method.equals("instock")) {
            // 下架，更新status=3即可
            this.itemRepository.updateByItemIds((byte) 2, itemIds);
        } else if (method.equals("delete")) {
            // 删除，更新status=3即可
            this.itemRepository.updateByItemIds((byte) 3, itemIds);
        }
        return new ShopResult(200, "ok", null);
    }

    // 添加
    @Transactional
    @CacheEvict(value = "itemService", allEntries = true)
    @Override
    public ShopResult addItem(Item item, String desc, String itemParams) {
        item.setStatus((byte) 1);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        item.setCreated(timestamp);
        item.setUpdated(timestamp);

        Item item1 = this.itemRepository.saveAndFlush(item);

        //添加商品描述
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item1.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(timestamp);
        itemDesc.setUpdated(timestamp);

        this.itemDescRepository.saveAndFlush(itemDesc);

        //把商品的规格参数插入到 商品规格和商品的关系表tb_item_param_item 中
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(item1.getId());
        itemParamItem.setParamData(itemParams);
        itemParamItem.setCreated(timestamp);
        itemParamItem.setUpdated(timestamp);

        this.itemParamItemRepository.saveAndFlush(itemParamItem);
        return new ShopResult(200, "ok", null);
    }

    //  根据id查询商品描述
    @CacheEvict(value = "itemService", allEntries = true)
    @Override
    public ShopResult getItemDesc(Long id) {
        ItemDesc itemDesc = this.itemDescRepository.getOne(id);
        return new ShopResult(200, "ok", itemDesc);
    }

    // 更新商品
    @Transactional
    @CacheEvict(value = "itemService", allEntries = true)
    @Override
    public ShopResult updateItem(Item item, String desc, String itemParams) {
        Item item1 = this.itemRepository.getOne(item.getId());
        item.setStatus(item1.getStatus());
        item.setCreated(item1.getCreated());
        // 1.根据商品id更新商品表
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        item.setUpdated(timestamp);
        this.itemRepository.saveAndFlush(item);
        // 2.根据商品id更新商品描述表
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item1.getId());
        itemDesc.setItemDesc(desc);

        ItemDesc itemDesc1 = this.itemDescRepository.getOne(item.getId());
        itemDesc.setCreated(itemDesc1.getCreated());
        itemDesc.setUpdated(timestamp);
        this.itemDescRepository.saveAndFlush(itemDesc);

        // 3.根据商品id更新 商品规格和商品的关系表tb_item_param_item
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(item1.getId());
        itemParamItem.setParamData(itemParams);
        itemParamItem.setCreated(itemDesc1.getCreated());
        itemParamItem.setUpdated(timestamp);

        this.itemParamItemRepository.saveAndFlush(itemParamItem);

        return new ShopResult(200, "ok", null);
    }

}
