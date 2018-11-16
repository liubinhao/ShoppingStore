package com.shop.dev.service;

import com.shop.dev.back_respository.ItemDescRepository;
import com.shop.dev.back_respository.ItemRepository;
import com.shop.dev.controller.response_web.ShopResult;
import com.shop.dev.entity.Item;
import com.shop.dev.entity.ItemDesc;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

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

    @Cacheable(value = "itemService")
    @Override
    public Page<Item> findItems(int page, int rows) {
        PageRequest pageable = PageRequest.of(page, rows);
        return this.itemRepository.findAll(pageable);
    }

    @Cacheable(value = "itemService")
    @Override
    public List<Item> findItems() {
        return this.itemRepository.findAll();
    }

    /**
     * @Author 刘树青 上下架及其删除
     * @Date 2018/11/12 15:00
     * @param: [itemIds, method]
     * return: com.shop.dev.controller.response_web.ShopResult
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
    public ShopResult addItem(Item item, String desc) {
        item.setStatus((byte) 1);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        item.setCreated(timestamp);
        item.setUpdated(timestamp);

        Item item1 = this.itemRepository.saveAndFlush(item);

        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item1.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(timestamp);
        itemDesc.setUpdated(timestamp);

        this.itemDescRepository.saveAndFlush(itemDesc);
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
    public ShopResult updateItem(Item item, String desc) {
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

        return new ShopResult(200, "ok", null);
    }

}
