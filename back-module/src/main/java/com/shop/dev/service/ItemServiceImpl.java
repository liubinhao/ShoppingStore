package com.shop.dev.service;

import com.shop.dev.back_respository.ItemRepository;
import com.shop.dev.controller.response_web.ItemResult;
import com.shop.dev.entity.Item;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
     * return: com.shop.dev.controller.response_web.ItemResult
     */
    @Transactional
    @CacheEvict(value = "itemService", allEntries = true)
    @Override
    public ItemResult updateItemStatus(List<Long> itemIds, String method) {
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
        return new ItemResult(200, "ok", null);
    }

//    /**
//     * @Author 刘树青 更新商品学习
//     * @Date 2018/11/12 19:33
//     * @param: [item, desc]
//     * return: int
//     */
//    @Override
//    public Item updateItem(Item item) {
//      return this.itemRepository.saveAndFlush(item);
//    }
}
