package com.shop.dev.service;

import com.shop.dev.entity.ItemParam;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ClassName ItemParamService
 * @Author 刘树青
 * @Date 2018/11/9 13:03
 * @Version 1.0
 */
public interface ItemParamService {

    Page<ItemParam> findItemParams(int page, int rows);

    List<ItemParam> findItemParams();
}
