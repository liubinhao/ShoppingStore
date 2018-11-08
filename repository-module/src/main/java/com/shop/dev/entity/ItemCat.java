package com.shop.dev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName ItemCat
 * @Author 刘树青
 * @Date 2018/11/7 17:16
 * @Version 1.0
 */
@Entity
@Table(name = "tb_item_cat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private Byte isParent;
    private Timestamp created;
    private Timestamp updated;
}
