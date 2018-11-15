package com.shop.dev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName Content
 * @Author 刘树青
 * @Date 2018/11/7 17:16
 * @Version 1.0
 */
@Entity
@Table(name = "tb_content")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long categoryId;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;
    private Timestamp created;
    private Timestamp updated;

}
