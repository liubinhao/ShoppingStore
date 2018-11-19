package com.shop.dev.controller;

import com.shop.dev.result_wrapper.EasyUITreeNode;
import com.shop.dev.result_wrapper.ShopResult;
import com.shop.dev.service.ContentCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ContentCategoryController
 * @Author 刘树青
 * @Date 2018/11/13 13:23
 * @Version 1.0
 */
@RestController
public class ContentCategoryController {
    @Resource
    private ContentCategoryService contentCategoryService;

    // 内容分类管理列表展示
    @RequestMapping("/content/category/list")
    public List<EasyUITreeNode> findContentCategories(@RequestParam(name = "id", defaultValue = "0") long parentId) {
        return this.contentCategoryService.findContentCategories(parentId);
    }

    // 添加
    @RequestMapping("/content/category/create")
    public ShopResult addContentCategory(Long parentId, String name) {
        return contentCategoryService.insertContentCategory(parentId, name);
    }

    // 重命名
    @RequestMapping("/content/category/update")
    public ShopResult updateContentCategory(Long id, String name) {
        return this.contentCategoryService.updateContentCategory(id, name);
    }

    // 删除
    @RequestMapping("/content/category/delete/")
    public ShopResult deleteContentCategory(Long id) {
        return contentCategoryService.deleteContentCategory(id);

    }

}
