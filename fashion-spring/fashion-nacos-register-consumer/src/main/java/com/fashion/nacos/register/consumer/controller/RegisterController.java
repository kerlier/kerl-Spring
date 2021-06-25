package com.fashion.nacos.register.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fashion.nacos.register.api.ITestService;
import com.fashion.nacos.register.api.RUserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangyuguang
 * @Date: 2021/5/13 9:11
 */
@RestController
public class RegisterController {

    @Reference
    private ITestService testService;

    @GetMapping("/hello")
    public RUserDTO getHello(){
        return testService.getUser();
    }

}
