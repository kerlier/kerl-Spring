package com.fashion.spring.service;

import com.fashion.spring.pojo.User;

public interface UserService {
    User findUserById(Integer userId);
}
