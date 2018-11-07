package com.shop.dev.commons;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pageable<T> {

    private int totalCount;
    private List<T> items;

}
