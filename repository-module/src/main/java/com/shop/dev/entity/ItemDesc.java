package com.shop.dev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName ItemDesc
 * @Author 刘树青
 * @Date 2018/11/7 17:16
 * @Version 1.0
 */
@Entity
@Table(name = "tb_item_desc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDesc {
    @Id
    private long itemId;
    private String itemDesc;
    private Timestamp created;
    private Timestamp updated;

}
