package com.shop.dev.repository;

import com.shop.dev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName UserRepository
 * @Author 刘树青
 * @Date 2018/11/8 9:19
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
