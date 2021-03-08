package com.nacos.config.application.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/8 15:43
 */
@RestController
public class ConfigController {

    @NacosValue(value = "${name:1}",autoRefreshed = true)
    private String info;

    @GetMapping("/info")
    public String getInfo(){
        return info;
    }
}
