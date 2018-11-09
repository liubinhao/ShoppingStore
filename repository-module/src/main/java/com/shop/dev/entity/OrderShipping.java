package com.shop.dev.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * if the class no bug ,the author is 高帅.
 * Else, emm  ... I don't know the author.
 * Create at: 2018/11/7上午 10:17
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_order_shipping")
public class OrderShipping {
    @Id
    private Integer orderId;
    private String receiverName;
    //    固定电话
    private String receiverPhone;
    //    移动电话
    private String receiverMobile;
    //    省份
    private String receiverState;
    private String receiverCity;
    //    区,县
    private String receiverDistrict;
    //    收货地址
    private String receiverAddress;
    //    邮编
    private String receiverZip;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updated;



}
