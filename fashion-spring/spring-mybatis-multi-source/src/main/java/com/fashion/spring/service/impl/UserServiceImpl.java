package com.fashion.spring.service.impl;

import com.fashion.spring.mapper.db1.UserMapper;
import com.fashion.spring.pojo.User;
import com.fashion.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(Integer userId) {
        return userMapper.findUserById(userId);
    }
}
