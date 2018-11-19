package com.shop.dev.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
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
// 该注解用于取消 hibernate 的懒加载,使代理对象生效
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class ItemDesc implements Serializable {
    @Id
    private long itemId;
    private String itemDesc;
    private Timestamp created;
    private Timestamp updated;

}
