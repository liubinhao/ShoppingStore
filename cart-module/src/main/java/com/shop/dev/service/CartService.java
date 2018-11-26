package com.shop.dev.service;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.controller.param.ItemParam;
import com.shop.dev.entity.Item;
import com.shop.dev.repository.ItemRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.*;


// author: mrd

@Service

public class CartService implements ICartService {

    @Resource
    private ItemRepository itemRepository;

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    private Long userId = 100L;



    // 将商品添加到购物车
    @Override
    public void addItem(ItemParam param) {
        HashOperations<Object, Object, Object> hash = redisTemplate.opsForHash();
        String key = "userId:"+ userId;
        String field = "itemId:" + param.getItemId();
        if (hash.hasKey(key, field)){
            hash.increment(key, field, param.getItemNum());
        }
        else {
            hash.put(key, field, param.getItemNum().toString());
        }
    }



    // 查看购物车中所有的商品
    @Override
    public List<?> showItemInformation() {
        HashOperations<Object, Object, Object> hash = redisTemplate.opsForHash();
        String key = "userId:" + userId;
        List<Object> itemList = new ArrayList<>();
        Set<Object> hkeys = hash.keys(key);
        for (Object s : hkeys) {
            Long itemId = Long.parseLong(s.toString().split(":")[1]);
            Map<String, Object> map = new HashMap<>();
            Item item = this.itemRepository.findById(itemId).get();
            map.put("item",item);
            String num = (String) hash.get(key, s);
            map.put("buyNum" , num);
            itemList.add(map);
        }
        return itemList;
    }

    // 删除某个商品
    @Override
    public void removeItem(Long itemId) {
        HashOperations<Object, Object, Object> hash = redisTemplate.opsForHash();
        hash.delete("userId:100", "itemId:"+itemId);
    }

    // 更新购物车中某商品的数量
    @Override
    public void updateItemQuantity(Long itemId, Integer buyNum) {
        HashOperations<Object, Object, Object> hash = redisTemplate.opsForHash();
        System.out.println(itemId + "..." + buyNum);
        hash.put("userId:100", "itemId:"+ itemId, buyNum.toString());
    }


    // 批量删除
    @Override
    public ResultWrapper batchDel(String[] keys) {
        HashOperations<Object, Object, Object> hash = redisTemplate.opsForHash();
        hash.delete("userId:100", keys);
        return ResultWrapper.success("删除成功");
    }


    // 根据 商品id 查询对应商品的具体信息
    @Override
    public ResultWrapper findItemByItemId(Long itemId) {
        Item item = this.itemRepository.findById(itemId).get();
        if (item != null){
            return ResultWrapper.success(item);
        }
        else{
            return ResultWrapper.error(250, "该商品不存在");
        }

    }


    /**
     *  查询出购物清单
     * @param itemIds
     * @return
     */
    @Override
    public ResultWrapper findShoppingList(String itemIds) {

        HashOperations<Object, Object, Object> hash = redisTemplate.opsForHash();
        String[] itemIdArr = itemIds.split(",");
        List<Object> itemList = new ArrayList<>();
        for (String field : itemIdArr) {
            long itemId = Long.parseLong(field);
            Map<String, Object> map = new HashMap<>();
            Item item = this.itemRepository.findById(itemId).get();
            map.put("item", item);
            String num = (String) hash.get("userId:100", "itemId:" + itemId);
            map.put("buyNum", num);
            itemList.add(map);
        }
//         生成订单后删除购物车中的商品
//        batchDel(itemIds);
        if (itemList.size() != 0){
            return ResultWrapper.success(itemList);
        } else {
            return ResultWrapper.error(333, "此用户生成的订单没有任何商品信息");
        }


    }


}
