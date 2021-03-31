package com.fashion.spring.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/31 14:20
 */
@RestController
public class SentinelController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
