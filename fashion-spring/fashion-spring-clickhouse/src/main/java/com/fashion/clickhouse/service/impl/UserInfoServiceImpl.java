package com.fashion.clickhouse.service.impl;

import com.fashion.clickhouse.mapper.UserInfoMapper;
import com.fashion.clickhouse.pojo.UserInfo;
import com.fashion.clickhouse.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/31 9:44
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void saveData(UserInfo userInfo) {
        userInfoMapper.saveData(userInfo);
    }

    @Override
    public UserInfo selectById(Integer id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public List<UserInfo> selectList() {
        return userInfoMapper.selectList();
    }
}
