package com.shop.dev.back_respository;

import com.shop.dev.entity.ItemCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName ItemCatRepository
 * @Author 刘树青
 * @Date 2018/11/8 19:36
 * @Version 1.0
 */
public interface ItemCatRepository extends JpaRepository<ItemCat, Long> {

    @Query("select id,name from ItemCat where parentId=?1")
    List<ItemCat> findByParentId(Long parentId);
}
