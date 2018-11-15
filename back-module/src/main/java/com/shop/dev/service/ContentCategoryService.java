package com.shop.dev.service;

import com.shop.dev.controller.response_web.EasyUIResult;
import com.shop.dev.controller.response_web.ShopResult;

import java.util.List;

/**
 * @ClassName ContentCategoryService
 * @Author 刘树青
 * @Date 2018/11/13 9:05
 * @Version 1.0
 */
public interface ContentCategoryService {

    List<EasyUIResult> findContentCategories(long parentId);

    //添加内容分类
    ShopResult insertContentCategory(long parentId, String name);

    //修改内容分类
    ShopResult updateContentCategory(long id, String name);

    //删除内容分类
    ShopResult deleteContentCategory(long id);

}
