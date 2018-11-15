package com.shop.dev.service;

import com.shop.dev.controller.response_web.ShopResult;
import com.shop.dev.entity.Content;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ContentService
 * @Author 刘树青
 * @Date 2018/11/15 9:15
 * @Version 1.0
 */
public interface ContentService {

    Map findContents(long categoryId, int page, int rows);

    ShopResult addContent(Content content);

    ShopResult updateContent(Content content);

    ShopResult deleteContent(List<Long> ids);
}
