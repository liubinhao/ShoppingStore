package com.shop.dev.service;

import com.shop.dev.back_respository.ItemCatRepository;
import com.shop.dev.entity.ItemCat;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ItemCatServiceImpl
 * @Author 刘树青
 * @Date 2018/11/8 19:37
 * @Version 1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Resource
    private ItemCatRepository itemCatRepository;

    @Cacheable(value = "itemCatService")
    @Override
    public List<ItemCat> findByParentId(Long parentId) {
        return this.itemCatRepository.findByParentId(parentId);
    }
}
