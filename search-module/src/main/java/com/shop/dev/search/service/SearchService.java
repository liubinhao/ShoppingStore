package com.shop.dev.search.service;

import com.shop.dev.commons.ResultWrapper;

/**
 * CREATE BY Liu.
 * ON 2018/11/7 14:15
 */
public interface SearchService {

   // 查询所有商品
   ResultWrapper findAll();
   // 根据商品Title查询商品
   ResultWrapper findAllByTitle(String title);
   // 根据商品Id查询商品
   ResultWrapper findAllByItemId(long itemId);
}
