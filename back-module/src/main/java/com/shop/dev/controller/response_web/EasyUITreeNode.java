package com.shop.dev.controller.response_web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName EasyUITreeNode
 * @Author 刘树青
 * @Date 2018/11/10 17:08
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EasyUITreeNode implements Serializable {
    private long id;
    private String text;
    private String state;
}
