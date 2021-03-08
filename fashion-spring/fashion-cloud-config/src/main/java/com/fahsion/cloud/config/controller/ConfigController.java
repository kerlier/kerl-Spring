package com.fahsion.cloud.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/8 15:13
 */
@RestController
public class ConfigController {

    @Value("${info}")
    private String version;

    @GetMapping("/info")
    public String getInfo(){
        return version;
    }
}
