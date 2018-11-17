package com.shop.dev.repository;

import com.shop.dev.entity.ItemParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName ItemParamRepository
 * @Author 刘树青
 * @Date 2018/11/9 12:59
 * @Version 1.0
 */
public interface ItemParamRepository extends JpaRepository<ItemParam, Long> {

    @Modifying
    @Query("delete from ItemParam where id in :ids")
    void deleteByIds(@Param("ids") List<Long> ids);

    ItemParam findByItemCatId(long itemCatId);

    @Query(value = "select count(*) from tb_item_param", nativeQuery = true)
    long findByCount();
}
