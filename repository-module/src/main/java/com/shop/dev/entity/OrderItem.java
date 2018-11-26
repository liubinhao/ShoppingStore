package com.shop.dev.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * if the class no bug ,the author is 高帅.
 * Else, emm  ... I don't know the author.
 * Create at: 2018/11/7上午 10:36
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_order_item")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})

public class OrderItem {
    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderitem_id")
    private Integer orderItemId;
    private String itemId;
    private Integer orderId;
    private Integer num;
    //    商品标签
    private String title;
    private Long price;
    //    商品总金额
    private Long totalFee;
    //    商品图片地址
    private String picPath;


    @OneToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId", insertable = false, updatable = false)
    private Item item;
//    @OneToOne
//    @JoinColumn(name = "orderId", referencedColumnName = "orderId", insertable = false, updatable = false)
//    private OrderShipping orderShipping;
}
