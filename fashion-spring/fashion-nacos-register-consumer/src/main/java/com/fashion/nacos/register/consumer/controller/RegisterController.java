package com.fashion.nacos.register.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fashion.nacos.register.api.ITestService;
import com.fashion.nacos.register.api.RUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangyuguang
 * @Date: 2021/5/13 9:11
 */
@RestController
public class RegisterController {
    private static Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    @Reference
    private ITestService testService;

    @GetMapping("/hello")
    public RUserDTO getHello(){
        LOGGER.info("打印日志");
        return testService.getUser();
    }

}
