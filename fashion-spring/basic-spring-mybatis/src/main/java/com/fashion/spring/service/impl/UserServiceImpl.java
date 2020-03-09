package com.fashion.spring.service.impl;

import com.fashion.spring.mapper.UserMapper;
import com.fashion.spring.pojo.User;
import com.fashion.spring.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserById(Integer userId) {
        return userMapper.findUserById(userId);
    }
}
