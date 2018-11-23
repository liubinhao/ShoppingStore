package com.shop.dev.user.service;

import com.shop.dev.entity.MyInfo;
import com.shop.dev.repository.MyInfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class MyInfoServiceImpl implements MyInfoService {
    @Resource
    private MyInfoRepository repository;
    @Override
    public MyInfo addOne(MyInfo myInfo) {
        MyInfo save = repository.save(myInfo);
        return save;
    }
}
