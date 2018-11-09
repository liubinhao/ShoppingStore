package com.shop.dev.controller.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemParam {

    private Long userId;
    private Integer itemNum;
    private Long itemId;
}
