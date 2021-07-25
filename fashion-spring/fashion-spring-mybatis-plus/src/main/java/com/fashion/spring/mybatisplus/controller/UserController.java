package com.fashion.spring.mybatisplus.controller;

import com.fashion.spring.mybatisplus.dto.UserInfo;
import com.fashion.spring.mybatisplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @Author: yangyuguang
 * @Date: 2021/5/14 10:30
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getById/{id}")
    public UserInfo getById(@PathVariable("id") Integer userId){
        ArrayList<UserInfo> objects = new ArrayList<>();

        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setUsername("name111");

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setId(1);
        userInfo1.setUsername("name222");

        objects.add(userInfo1);
        objects.add(userInfo);
        userService.update(objects);
        return null;
    }

    @GetMapping("/getByUsername")
    public UserInfo getByUsername(@RequestParam("search")String search){
        return userService.getByUsername(search);
    }
}
