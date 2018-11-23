package com.shop.dev.repository;

import com.shop.dev.entity.ContentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName ContentCategoryRepository
 * @Author 刘树青
 * @Date 2018/11/13 9:01
 * @Version 1.0
 */
public interface ContentCategoryRepository extends JpaRepository<ContentCategory, Long> {

    @Query(value = "select * from tb_content_category where parent_id=?1 and status=1", nativeQuery = true)
    List<ContentCategory> findByParentId(long parentId);

    @Query(value = "select * from tb_content_category where id=?1", nativeQuery = true)
    ContentCategory findByPId(long id);

}
