package com.shop.dev.service;

import com.shop.dev.back_respository.ItemDescRepository;
import com.shop.dev.entity.ItemDesc;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName ItemDescServiceImpl
 * @Author 刘树青
 * @Date 2018/11/12 15:40
 * @Version 1.0
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Resource
    private ItemDescRepository itemDescRepository;

    @Cacheable(value = "itemDescService")
    @Override
    public ItemDesc findByitemId(long itemId) {
        return this.itemDescRepository.getOne(itemId);
    }

//    @Override
//    public ItemDesc updateItemDesc(ItemDesc itemDesc) {
//        return this.itemDescRepository.saveAndFlush(itemDesc);
//    }
}
