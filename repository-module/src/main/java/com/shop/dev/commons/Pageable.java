package com.shop.dev.commons;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Pageable<T> {

    private Integer page;
    private Integer size;
    private int totalCount;
    private List<T> items;

    public Pageable(Integer page, Integer size, int totalCount, List<T> items) {
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;
        this.items = items;
    }
}
