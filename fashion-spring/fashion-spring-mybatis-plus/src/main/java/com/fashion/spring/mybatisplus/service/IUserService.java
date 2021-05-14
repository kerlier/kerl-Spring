package com.fashion.spring.mybatisplus.service;

import com.fashion.spring.mybatisplus.dto.UserInfo;

/**
 * @Author: yangyuguang
 * @Date: 2021/5/14 10:29
 */
public interface IUserService {

    UserInfo getById(Integer userId);

    UserInfo getByUsername(String search);
}
