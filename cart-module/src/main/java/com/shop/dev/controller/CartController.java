package com.shop.dev.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shop.dev.commons.ResultWrapper;
import com.shop.dev.controller.param.ItemParam;
import com.shop.dev.service.ICartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

// author: mrd


@RequestMapping("/cart")
@RestController
public class CartController {

    @Resource
    private ICartService iCartService;

    /**
     * 添加商品到购物车
     * @param item 保存着商品的 id 和购买的数量
     * @return
     */
    @PostMapping("/addItem")
    public ResultWrapper addItemToCart(ItemParam item){
        iCartService.addItem(item);
        return ResultWrapper.success("添加成功");
    }

    /**
     *  查看购物车列表
     * @return
     */
    @RequestMapping("/showItem")
    public Object showItem(){

        List<?> list = this.iCartService.showItemInformation();
        return ResultWrapper.success(list);
    }


    @GetMapping("/item/{itemId}")
    public ResultWrapper findItemByItemId(@PathVariable Long itemId){
        return this.iCartService.findItemByItemId(itemId);
    }




    /**
     *  从购物车中移除一个商品
     * @param itemId 移除商品的Id
     * @return
     */
    @PostMapping("/cartRemItem/{itemId}")
    public ResultWrapper removeItem(@PathVariable Long itemId){
        System.out.println("删除接收到的:" + itemId);

//        this.iCartService.removeItem(itemId);
        return ResultWrapper.success("删除商品Id:"+itemId+"成功");
    }


    /**
     * 批量从购物车中移除商品
     * @param keys
     * @return
     */
    @PostMapping("/batchRem")
    public ResultWrapper bathRem(@RequestParam(value = "str") String keys){

       return this.iCartService.batchDel(keys);
    }


    /**
     * 更新购物车中某个商品的数量
     * @param itemId    商品的id
     * @param buyNum    更新的商品数量
     * @return
     */
    @PostMapping("/updItemNum/{itemId}/{buyNum}")
    public ResultWrapper updItemNum(@PathVariable Long itemId, @PathVariable Integer buyNum){
        this.iCartService.updateItemQuantity(itemId, buyNum);
        return ResultWrapper.success("更新商品数量成功");
    }


    /**
     *  查询出购物清单
      * @param itemIds
     * @return
     */
    @GetMapping("/findShoppingList")
    public ResultWrapper shoppingList(String itemIds) {
      return this.iCartService.findShoppingList(itemIds);
    }





}
