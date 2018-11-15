package com.shop.dev.service;

import com.shop.dev.back_respository.ItemCatRepository;
import com.shop.dev.controller.response_web.EasyUIResult;
import com.shop.dev.entity.ItemCat;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public List<EasyUIResult> findItemCat(long itemCatId) {
        List<EasyUIResult> list = new ArrayList<>();

        List<ItemCat> itemCats = this.itemCatRepository.findItemCats(itemCatId);
        for (ItemCat itemCat : itemCats) {
            EasyUIResult easyUIResult = new EasyUIResult();
            easyUIResult.setId(itemCat.getId());
            easyUIResult.setText(itemCat.getName());
            easyUIResult.setState(itemCat.getIsParent() == 0 ? "closed" : "open");
            list.add(easyUIResult);
        }
        return list;
    }
}
