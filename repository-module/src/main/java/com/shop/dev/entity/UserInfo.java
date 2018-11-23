package com.shop.dev.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户信息
 * lhw
 */
@Entity
@Data
@Table(name = "tb_user")
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Timestamp created;
    private Timestamp updated;






}
