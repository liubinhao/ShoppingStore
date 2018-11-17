package com.shop.dev.controller;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.controller.param.ItemParam;
import com.shop.dev.entity.Item;
import com.shop.dev.service.ICartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping("/cart")
@Controller
public class CartController {

    @Resource
    private ICartService iCartService;

    @RequestMapping("/addItem")
    public Object addItemToCart(@RequestBody ItemParam item){
        System.out.println("-------------------");
//        System.out.println(UserId);
        System.out.println(item);
//        System.out.println(itemNum);
        iCartService.addItem(item);
        return null;
    }

    @RequestMapping("/showItem")
    @ResponseBody
    public Object showItem(Long userId){

        List<?> list = this.iCartService.showItemInformation(userId);
        return ResultWrapper.success(list);
    }

}
