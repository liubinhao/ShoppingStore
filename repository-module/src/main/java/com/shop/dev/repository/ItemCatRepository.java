package com.shop.dev.repository;

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

    @Query(value = "select * from shop.tb_item_cat where parent_id=?1 and status=1", nativeQuery = true)
    List<ItemCat> findByParentId(long parentId);
}
