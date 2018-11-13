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

    @Query(value = "select distinct tic1.*\n" +
            "from tb_item_cat tic1,\n" +
            "     tb_item_cat tic2\n" +
            "where tic1.parent_id = tic2.is_parent\n" +
            "  and tic1.parent_id = ?1", nativeQuery = true)
    List<ItemCat> findItemCats(long parentId);
}
