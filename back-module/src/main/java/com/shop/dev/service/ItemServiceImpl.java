package com.shop.dev.service;

import com.shop.dev.back_respository.ItemRepository;
import com.shop.dev.entity.Item;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

}
