package com.shop.dev.repository;

import com.shop.dev.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * CREATE BY Liu.
 * ON 2018/11/7 11:12
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
        List<Item> findAllByTitleContains(String title);

}
