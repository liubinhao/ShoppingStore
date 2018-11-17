package com.shop.dev.service;

import com.shop.dev.repository.ItemCatRepository;
import com.shop.dev.result_wrapper.EasyUITreeNode;
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
    public List<EasyUITreeNode> findItemCat(long itemCatId) {
        List<EasyUITreeNode> list = new ArrayList<>();

        List<ItemCat> itemCats = this.itemCatRepository.findByParentId(itemCatId);
        for (ItemCat itemCat : itemCats) {
            EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
            easyUITreeNode.setId(itemCat.getId());
            easyUITreeNode.setText(itemCat.getName());
            easyUITreeNode.setState(itemCat.getIsParent() == 1 ? "closed" : "open");
            list.add(easyUITreeNode);
        }
        return list;
    }
}
