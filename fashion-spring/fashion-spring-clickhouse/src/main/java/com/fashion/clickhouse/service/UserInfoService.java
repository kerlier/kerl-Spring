package com.fashion.clickhouse.service;

import com.fashion.clickhouse.pojo.UserInfo;

import java.util.List;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/31 9:43
 */
public interface UserInfoService {
    void saveData(UserInfo userInfo);

    UserInfo selectById(Integer id);

    List<UserInfo> selectList();
}
