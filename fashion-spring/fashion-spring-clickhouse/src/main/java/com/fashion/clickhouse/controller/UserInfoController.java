package com.fashion.clickhouse.controller;

import com.fashion.clickhouse.pojo.UserInfo;
import com.fashion.clickhouse.service.UserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/31 9:43
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/saveData")
    public String saveData(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(4);
        userInfo.setUsername("yangyuguang");
        userInfo.setPhone("18814665667");
        userInfo.setEmail("22@qq.com");
        userInfo.setCreateDay("2021-03-30");
        userInfoService.saveData(userInfo);
        return "suc";
    }


    @GetMapping("/selectById")
    public UserInfo selectById(){
        return userInfoService.selectById(1);
    }
}
