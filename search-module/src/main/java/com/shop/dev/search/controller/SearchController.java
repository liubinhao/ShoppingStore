package com.shop.dev.search.controller;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.search.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * CREATE BY Liu.
 * ON 2018/11/7 11:03
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Resource
    private SearchService searchService;

    /**
     * 查询所有商品
     * @return 商品信息
     */
    @GetMapping("/findAll")
    public ResultWrapper findAllItem(){
        return searchService.findAll();
    }

    /**
     * 根据商品Title查询商品
     * @param title 商品Title
     * @return 商品信息
     */
    @GetMapping("/findItemByTitle")
    public ResultWrapper findItemByTitle(@RequestParam(value = "title", defaultValue = "") String title){
       return searchService.findAllByTitle(title);
    }

    /**
     * 根据商品Id查询商品
     * @param itemId 商品Id
     * @return 商品信息
     */
    @GetMapping("/findItemByItemId")
    public ResultWrapper findItemByItemId(long itemId){
        return searchService.findAllByItemId(itemId);
    }
}


