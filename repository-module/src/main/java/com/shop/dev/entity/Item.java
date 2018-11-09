package com.shop.dev.entity;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * CREATE BY Liu.
 * ON 2018/11/7 11:55
 */
@Entity
@Data
@Table(name = "tb_item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private Long cid;
    private Integer status;
    private String title;
    private String sellPoint;
    private Double price;
    private Integer num;
    private String barcode;
    private String image;
    private Timestamp created;
    private  Timestamp updated;


}
