package com.fashion.spring.mybatisplus.service;

import com.fashion.spring.mybatisplus.dto.UserInfo;

import java.util.List;

/**
 * @Author: yangyuguang
 * @Date: 2021/5/14 10:29
 */
public interface IUserService {

    UserInfo getById(Integer userId);

    UserInfo getByUsername(String search);

    void update(List<UserInfo> userInfos) throws RuntimeException;
}
