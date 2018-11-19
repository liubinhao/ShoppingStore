package com.shop.dev.service;

import com.shop.dev.repository.ContentRepository;
import com.shop.dev.result_wrapper.ShopResult;
import com.shop.dev.entity.Content;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ContentServiceImpl
 * @Author 刘树青
 * @Date 2018/11/15 9:17
 * @Version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Resource
    private ContentRepository contentRepository;

    // 查看
    @Cacheable(value = "contentService")
    @Override
    public Map findContents(long categoryId, int page, int rows) {
        Pageable pageable = PageRequest.of(page, rows);
        Page<Content> contents = this.contentRepository.findByCategoryId(categoryId, pageable);
        long total = this.contentRepository.findByCount(categoryId);

        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", contents.getContent());
        return map;
    }

    // 添加
    @Transactional
    @CacheEvict(value = "contentService", allEntries = true)
    @Override
    public ShopResult addContent(Content content) {
        content.setCreated(new Timestamp(System.currentTimeMillis()));
        content.setUpdated(new Timestamp(System.currentTimeMillis()));
        this.contentRepository.saveAndFlush(content);
        return new ShopResult(200, "ok", null);
    }

    // 更新
    @Transactional
    @CacheEvict(value = "contentService", allEntries = true)
    @Override
    public ShopResult updateContent(Content content) {
        Content ct = this.contentRepository.getOne(content.getId());
        content.setCreated(ct.getCreated());
        content.setUpdated(new Timestamp(System.currentTimeMillis()));
        this.contentRepository.saveAndFlush(content);
        return new ShopResult(200, "ok", null);
    }

    // 删除
    @Transactional
    @CacheEvict(value = "contentService", allEntries = true)
    @Override
    public ShopResult deleteContent(List<Long> ids) {
        this.contentRepository.deleteAllById(ids);
        return new ShopResult(200, "ok", null);
    }


}
