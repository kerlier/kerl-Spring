package com.fashion.spring.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.fashion.spring.sentinel.handler.SentinelExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/31 14:20
 */
@RestController
public class SentinelController {

    @GetMapping("/hello")
    @SentinelResource(value = "hotkey",
            blockHandlerClass = SentinelExceptionHandler.class,
            blockHandler="blockExceptionHandler")
    public String hello(){
        return "hello";
    }
}
