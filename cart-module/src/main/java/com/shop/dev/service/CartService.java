package com.shop.dev.service;

import com.shop.dev.controller.param.ItemParam;
import com.shop.dev.entity.Item;
import com.shop.dev.repository.ItemRepository;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CartService implements ICartService {

    @Resource
    private ItemRepository itemRepository;



    // 将商品添加到购物车
    @Override
    public void addItem(ItemParam param) {
        Jedis jedis = new Jedis();
        String key = "userId:"+ param.getUserId();
        String field = "itemId:" + param.getItemId();
        if (jedis.hexists(key, field)) {
            jedis.hincrBy(key, field, param.getItemNum());
        }
        else {
            jedis.hset(key, "itemId:" + param.getItemId(), param.getItemNum().toString());
            System.out.println(jedis.hgetAll("userId:" + param.getUserId()));
        }
        jedis.close();

    }



    // 查看购物车中所有的商品
    @Override
    public List<?> showItemInformation(Long userId) {
        Jedis jedis = new Jedis();
        String key = "userId:" + userId;
        List<Object> itemList = new ArrayList<>();
        Set<String> hkeys = jedis.hkeys(key);
        for (String s : hkeys) {
            Long itemId = Long.parseLong(s.split(":")[1]);
            System.out.println("========-------------->:" + itemId);
            Map<String, Object> map = new HashMap<>();
            Item item = this.itemRepository.findById(itemId).get();
            map.put("item",item);
            String num = jedis.hget(key, "itemId:" + itemId);
            jedis.close();
            map.put("buyNum" , num);
            itemList.add(map);
        }
        jedis.close();
        return itemList;
    }

    // 删除某个商品
    @Override
    public void removeItem(Long itemId) {
        Jedis jedis = new Jedis();
        jedis.hdel("userId:100", "itemId:"+itemId);
        jedis.close();
    }

    // 更新购物车中某商品的数量
    @Override
    public void updateItemQuantity(ItemParam itemParam) {
        Jedis jedis = new Jedis();
        jedis.hset("userId:100", "itemId:"+ itemParam.getItemId(), itemParam.getItemNum().toString());
        jedis.close();
    }


}
