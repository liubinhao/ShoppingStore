package com.shop.dev.repository;

import com.shop.dev.entity.MyInfo;
import com.shop.dev.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo,Long> {
    /**
     * 按username查
     * @param username
     * @return
     * lhw
     */
    UserInfo findByUsername(String username);
    UserInfo findByPhone(String phone);





}
