package com.basic.spring.bean.post.environmentAware;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/5 17:22
 */
@RequestMapping
@RestController
public class My4Controller {

    @Resource
    private MyEnvironmentConfig myEnvironmentConfig;

    @GetMapping("get4")
    public String getProperty(){
        return myEnvironmentConfig.getProperty("password");
    }
}
