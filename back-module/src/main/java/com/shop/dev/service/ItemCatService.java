package com.shop.dev.service;

import com.shop.dev.controller.response_web.EasyUIResult;

import java.util.List;

/**
 * @ClassName ItemCatService
 * @Author 刘树青
 * @Date 2018/11/8 19:37
 * @Version 1.0
 */
public interface ItemCatService {

    List<EasyUIResult> findItemCat(long itemCatId);
}
