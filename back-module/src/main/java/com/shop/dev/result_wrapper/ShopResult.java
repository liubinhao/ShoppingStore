package com.shop.dev.result_wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName ShopResult
 * @Author 刘树青
 * @Date 2018/11/12 10:32
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopResult implements Serializable {
    private Integer status;
    private String msg;
    private Object data;
}
