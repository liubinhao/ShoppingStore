package com.shop.dev.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name = "tb_my_info")
public class MyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer infoId;
    private String infoName;
    private String gender;
    private Date birthday;
    private String hobby;
    private String email;
    private String realName;
    private String location;
    private String province;
    private Long userId;


}
