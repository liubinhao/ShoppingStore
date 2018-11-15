package com.shop.dev.controller;

import com.shop.dev.controller.response_web.EasyUIResult;
import com.shop.dev.controller.response_web.ShopResult;
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

    @RequestMapping("/content/category/list")
    public List<EasyUIResult> findContentCategories(@RequestParam(name = "id", defaultValue = "0") long parentId) {
        return this.contentCategoryService.findContentCategories(parentId);
    }

    @RequestMapping("/content/category/create")
    public ShopResult addContentCategory(Long parentId, String name) {
        return contentCategoryService.insertContentCategory(parentId, name);
    }

    @RequestMapping("/content/category/update")
    public ShopResult updateContentCategory(Long id, String name) {
        return this.contentCategoryService.updateContentCategory(id, name);
    }

    @RequestMapping("/content/category/delete/")
    public ShopResult deleteContentCategory(Long id) {
        return contentCategoryService.deleteContentCategory(id);

    }

}
