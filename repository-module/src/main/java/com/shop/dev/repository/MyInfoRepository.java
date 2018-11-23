package com.shop.dev.repository;

import com.shop.dev.entity.MyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyInfoRepository extends JpaRepository<MyInfo,Integer> {
}
