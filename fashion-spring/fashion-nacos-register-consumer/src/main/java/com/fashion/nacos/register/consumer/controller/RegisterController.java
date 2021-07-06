package com.fashion.nacos.register.consumer.controller;

import com.fashion.nacos.register.api.ITestService;
import com.fashion.nacos.register.api.RUserDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangyuguang
 * @Date: 2021/5/13 9:11
 */
@RestController
public class RegisterController {

    @Reference(timeout = 3000)
    private ITestService testService;

    @GetMapping("/hello")
    public RUserDTO getHello(){
        return testService.getUser();
    }

}
