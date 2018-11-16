package com.shop.dev.back_respository;

import com.shop.dev.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName ContentRepository
 * @Author 刘树青
 * @Date 2018/11/15 9:14
 * @Version 1.0
 */
public interface ContentRepository extends JpaRepository<Content, Long> {

    Page<Content> findByCategoryId(Long categoryId, Pageable pageable);

    @Query(value = "select count(*) from tb_content where category_id=?1", nativeQuery = true)
    long findByCount(Long categoryId);

    @Modifying
    @Query("delete from Content where id in :ids")
    void deleteAllById(@Param("ids") List<Long> ids);
}
