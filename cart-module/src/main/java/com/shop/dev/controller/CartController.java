package com.shop.dev.controller;

import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.controller.param.ItemParam;
import com.shop.dev.service.ICartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


@RequestMapping("/cart")
@RestController
public class CartController {

    @Resource
    private ICartService iCartService;

    @PostMapping("/addItem")
    public ResultWrapper addItemToCart(@RequestBody ItemParam item){
        iCartService.addItem(item);
        return ResultWrapper.success("添加成功");
    }

    @RequestMapping("/showItem")
    public Object showItem(Long userId){

        List<?> list = this.iCartService.showItemInformation(userId);
        return ResultWrapper.success(list);
    }

    @PostMapping("/cartRemItem/{itemId}")
    public ResultWrapper removeItem(@PathVariable Long itemId){
        System.out.println("删除接收到的:" + itemId);

//        this.iCartService.removeItem(itemId);
        return ResultWrapper.success("删除商品Id:"+itemId+"成功");
    }


    @PostMapping("/batchRem")
    public ResultWrapper bathRem(@RequestParam(value = "itemIds") int[] itemIds){
        System.out.println("数组:" + Arrays.toString(itemIds));
        return ResultWrapper.success("OK");
    }


    @PostMapping("/updItemNum")
    public ResultWrapper updItemNum(@RequestBody ItemParam itemParam){
        this.iCartService.updateItemQuantity(itemParam);
        return ResultWrapper.success("更新商品数量成功");
    }



}
