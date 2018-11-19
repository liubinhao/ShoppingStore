package com.shop.dev.service;

import com.shop.dev.result_wrapper.EasyUITreeNode;
import com.shop.dev.result_wrapper.ShopResult;
import com.shop.dev.entity.ItemParam;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ItemParamService
 * @Author 刘树青
 * @Date 2018/11/9 13:03
 * @Version 1.0
 */
public interface ItemParamService {
    // 查
    Map findItemParams(int page, int rows);

    // 删
    ShopResult deleteItemParam(List<Long> ids);

    ShopResult getItemCatByCid(Long cid);

    ShopResult insertItemParam(Long id, String paramData);

}
