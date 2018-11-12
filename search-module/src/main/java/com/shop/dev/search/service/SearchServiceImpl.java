package com.shop.dev.search.service;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.entity.Item;
import com.shop.dev.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * CREATE BY Liu.
 * ON 2018/11/7 14:15
 */
@Service
@Transactional
public class SearchServiceImpl implements SearchService{

    @Resource
    private ItemRepository repository;

    /**
     * 查询所有商品
     * @return  如果查询到商品返回商品信息, 如果没有查询到, 返回错误信息
     */
    @Override
    @Transactional(readOnly = true)
    public ResultWrapper findAll() {
        List<Item> items = repository.findAll();
        if (!items.isEmpty()){
            return ResultWrapper.success(items);
        }
        return ResultWrapper.error(420, "商品不见了!");
    }

    /**
     * 根据商品Title查询商品
     * @param title 商品Title
     * @return 如果查询到商品返回商品信息, 如果没有查询到, 返回错误信息
     */
    @Override
    public ResultWrapper findAllByTitle(String title) {
        List<Item> items = repository.findAllByTitleContains(title);
        if (!items.isEmpty()){
            return ResultWrapper.success(items);
        }
        return ResultWrapper.error(411, "找不到这个商品!");
    }

    /**
     * 根据商品Id查询商品
     * @param itemId 商品Id
     * @return 如果查询到商品返回商品信息, 如果没有查询到, 返回错误信息
     */
    @Override
    public ResultWrapper findAllByItemId(long itemId) {
        Optional<Item> item = repository.findById(itemId);
        return item.map(ResultWrapper::success).orElseGet(() -> ResultWrapper.error(411, "找不到这个商品!"));
    }


}
