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

    @Override
    public ItemParam addItem(ItemParam itemParam) {
//        Long itemId = param.getItemId();
//        Item item = itemRepository.findById(itemId).get();
//        System.out.println("item:  " + item);

        Jedis jedis = new Jedis();
        String key = "userId:"+ itemParam.getUserId();
        String field = "itemId:" + itemParam.getItemId();
        if (jedis.hexists(key, field)) {
            jedis.hincrBy(key, field, itemParam.getItemNum());
            System.out.println("进来了,有一样的+++++++++++++++");
        }
        else {
            jedis.hset(key, "itemId:" + itemParam.getItemId(), itemParam.getItemNum().toString());
            System.out.println(jedis.hgetAll("userId:" + itemParam.getUserId()));
        }
          jedis.close();

        return null;
    }

    @Override
    public List<?> showItemInformation(Long userId) {
        Jedis jedis = new Jedis();
        String key = "userId:" + userId;
        List<Object> list = new ArrayList<>();
        Set<String> hkeys = jedis.hkeys(key);
        for (String s : hkeys) {
//            Long itemId = Long.parseLong(s.substring(s.indexOf(":", 1) + 1, s.length()));
            Long itemId = Long.parseLong(s.split(":")[1]);
            System.out.println("========-------------->:" + itemId);
            Map<String, Object> map = new HashMap<>();
            Item item = this.itemRepository.findById(itemId).get();
            map.put("item",item);
            String num = jedis.hget(key, "itemId:" + itemId);
            jedis.close();
            map.put("buyNum" , num);

            list.add(map);
        }

        return list;
    }


}
