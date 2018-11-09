package com.shop.dev.service;

import com.shop.dev.back_respository.ItemParamRepository;
import com.shop.dev.entity.ItemParam;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ItemParamServiceImpl
 * @Author 刘树青
 * @Date 2018/11/9 13:03
 * @Version 1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Resource
    private ItemParamRepository itemParamRepository;

    @Cacheable(value = "itemParamService")
    @Override
    public Page<ItemParam> findItemParams(int page, int rows) {
        PageRequest pageable = PageRequest.of(page, rows);
        return this.itemParamRepository.findAll(pageable);
    }

    @Cacheable(value = "itemParamService")
    @Override
    public List<ItemParam> findItemParams() {
        return this.itemParamRepository.findAll();
    }
}
