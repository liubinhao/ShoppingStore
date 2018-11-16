package com.shop.dev.controller;

import com.shop.dev.result_wrapper.ShopResult;
import com.shop.dev.entity.Content;
import com.shop.dev.service.ContentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ContentController
 * @Author 刘树青
 * @Date 2018/11/15 9:23
 * @Version 1.0
 */
@RestController
public class ContentController {
    @Resource
    private ContentService contentService;

    @RequestMapping("/content/query/list")
    public Map findContents(@RequestParam(defaultValue = "0") long categoryId, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int rows) {
        return this.contentService.findContents(categoryId, page - 1, rows);
    }

    @RequestMapping("/content/save")
    public ShopResult addContent(Content content) {
        return this.contentService.addContent(content);
    }

    @RequestMapping("/content/edit")
    public ShopResult updateContent(Content content) {
        return this.contentService.updateContent(content);
    }

    @RequestMapping("/content/delete")
    public ShopResult deleteContent(@RequestParam(value = "ids") List<Long> ids) {
        return this.contentService.deleteContent(ids);
    }
}
