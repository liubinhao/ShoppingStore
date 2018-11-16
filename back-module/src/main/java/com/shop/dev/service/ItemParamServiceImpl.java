package com.shop.dev.service;

import com.shop.dev.back_respository.ItemParamRepository;
import com.shop.dev.result_wrapper.EasyUITreeNode;
import com.shop.dev.result_wrapper.ShopResult;
import com.shop.dev.entity.ItemParam;
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
 * @ClassName ItemParamServiceImpl
 * @Author 刘树青
 * @Date 2018/11/9 13:03
 * @Version 1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Resource
    private ItemParamRepository itemParamRepository;

    // 查
    @Cacheable(value = "itemParamService")
    @Override
    public Map findItemParams(int page, int rows) {
        // 遇到的坑:在PageRequest类中,计算的页码是从0开始的,而前端最小的页面则是1
        Pageable pageable = PageRequest.of(page - 1, rows);
        Page<ItemParam> itemParams = this.itemParamRepository.findAll(pageable);
        List<ItemParam> itemParamList = this.itemParamRepository.findAll();
        Map map = new HashMap();
        map.put("total", itemParamList.size());
        map.put("rows", itemParams.getContent());
        return map;
    }

    // 删
    @Transactional
    @CacheEvict(value = "itemParamService", allEntries = true)
    @Override
    public ShopResult deleteItemParam(List<Long> ids) {
        this.itemParamRepository.deleteByIds(ids);
        return new ShopResult(200, "ok", null);
    }

    // 通过商品类目id获取规格参数模板
    @Cacheable(value = "itemParamService")
    @Override
    public ShopResult getItemCatByCid(Long cid) {
        ItemParam itemParam = this.itemParamRepository.findByItemCatId(cid);
        return new ShopResult(200, "ok", itemParam);
    }

    // 插入规格参数模板
    @Transactional
    @CacheEvict(value = "itemParamService", allEntries = true)
    @Override
    public ShopResult insertItemParam(Long cid, String paramData) {
        //创建一个pojo
        ItemParam itemParam = new ItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        itemParam.setCreated(timestamp);
        itemParam.setUpdated(timestamp);
        //插入记录
        this.itemParamRepository.saveAndFlush(itemParam);
        return new ShopResult(200, "ok", null);
    }
}
