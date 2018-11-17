package com.shop.dev.service;

import com.shop.dev.repository.ContentCategoryRepository;
import com.shop.dev.result_wrapper.EasyUITreeNode;
import com.shop.dev.result_wrapper.ShopResult;
import com.shop.dev.entity.ContentCategory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ContentCategoryServiceImpl
 * @Author 刘树青
 * @Date 2018/11/13 9:06
 * @Version 1.0
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Resource
    private ContentCategoryRepository contentCategoryRepository;

    @Cacheable(value = "contentCategoryService")
    @Override
    public List<EasyUITreeNode> findContentCategories(long parentId) {
        // 根据一级类目查询
        List<ContentCategory> contentCategories = this.contentCategoryRepository.findByParentId(parentId);

        List<EasyUITreeNode> easyUITreeNodeList = new ArrayList<>();

        for (ContentCategory contentCategory : contentCategories) {
            EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
            easyUITreeNode.setId(contentCategory.getId());
            easyUITreeNode.setText(contentCategory.getName());
            // 若果该节点是父类目,就关闭
            easyUITreeNode.setState(contentCategory.getIsParent() == 1 ? "closed" : "open");
            easyUITreeNodeList.add(easyUITreeNode);
        }
        return easyUITreeNodeList;
    }

    // 添加
    @Transactional
    @CacheEvict(value = "contentCategoryService", allEntries = true)
    @Override
    public ShopResult insertContentCategory(long parentId, String name) {

        // 设置默认值
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setName(name);
        contentCategory.setParentId(parentId);
        contentCategory.setIsParent((byte) 0);
        contentCategory.setSortOrder(1);
        contentCategory.setStatus(1);

        contentCategory.setCreated(new Timestamp(System.currentTimeMillis()));
        contentCategory.setUpdated(new Timestamp(System.currentTimeMillis()));

        this.contentCategoryRepository.saveAndFlush(contentCategory);

        //添加一个节点需要判断父节点是不是叶子节点，如果父节点是叶子节点的话，需要改成父节点状态
        ContentCategory parent = this.contentCategoryRepository.findByPId(parentId);
        if (parent.getIsParent() == 0) {
            parent.setIsParent((byte) 1);
            this.contentCategoryRepository.saveAndFlush(parent);
        }
        return new ShopResult(200, "ok", contentCategory);
    }

    // 更新
    @Transactional
    @CacheEvict(value = "contentCategoryService", allEntries = true)
    @Override
    public ShopResult updateContentCategory(long id, String name) {
        //通过id查询节点对象
        ContentCategory contentCategory = this.contentCategoryRepository.getOne(id);
        //判断新的name值与原来的值是否相同，如果相同则不用更新
        if (name != null && name.equals(contentCategory.getName())) {
            return new ShopResult(200, "ok", null);
        }
        contentCategory.setName(name);
        //设置更新时间
        contentCategory.setUpdated(new Timestamp(System.currentTimeMillis()));
        //更新数据库
        this.contentCategoryRepository.saveAndFlush(contentCategory);
        //返回结果
        return new ShopResult(200, "ok", null);

    }


    // 删除
    @Transactional
    @CacheEvict(value = "contentCategoryService", allEntries = true)
    @Override
    public ShopResult deleteContentCategory(long id) {

        ContentCategory contentCategory = this.contentCategoryRepository.getOne(id);
        //判断删除的节点是否为父类
        if (contentCategory.getIsParent() == 1) {
            List<ContentCategory> list = this.contentCategoryRepository.findByParentId(id);
            //递归删除
            for (ContentCategory c : list) {
                deleteContentCategory(c.getId());
            }
        }
        //判断父类中是否还有子类节点，没有的话，把父类改成子类
        if (this.contentCategoryRepository.findByParentId(contentCategory.getParentId()).size() == 1) {
            ContentCategory parentCategory = this.contentCategoryRepository.getOne(contentCategory.getParentId());
            parentCategory.setIsParent((byte)0);
            this.contentCategoryRepository.saveAndFlush(parentCategory);
        }
        contentCategory.setStatus(2);
        this.contentCategoryRepository.saveAndFlush(contentCategory);
        return new ShopResult(200, "ok", null);
    }
    
}
