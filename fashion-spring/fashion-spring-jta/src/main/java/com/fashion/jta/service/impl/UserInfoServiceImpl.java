package com.fashion.jta.service.impl;

import com.fashion.jta.mapper.one.UserInfo01Mapper;
import com.fashion.jta.mapper.two.UserInfo02Mapper;
import com.fashion.jta.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: yangyuguang
 * @Date: 2021/4/1 10:11
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfo01Mapper userInfo01Mapper;

    @Autowired
    private UserInfo02Mapper userInfo02Mapper;

    @Transactional
    @Override
    public void transfer() {
        userInfo01Mapper.transfer();
        int i = 1/0;
        userInfo02Mapper.transfer();
    }
}
