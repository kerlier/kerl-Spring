package com.nacos.config.application.controller;

import com.nacos.config.application.config.NacosConfig;
import com.nacos.config.application.config.NacosConfig2;
import com.nacos.config.application.config.NacosConfig3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/8 15:43
 */
@RestController
public class ConfigController {

//    @NacosValue(value = "${name:1}",autoRefreshed = true)
//    private String info;
    @Autowired
    private NacosConfig nacosConfig;

    @Autowired
    private NacosConfig2 nacosConfig2;

    @Autowired
    private NacosConfig3 nacosConfig3;

//    @NacosValue("${password}")
//    private String password;


    @GetMapping("/name")
    public String getName(){
        return nacosConfig.getName();
    }

    @GetMapping("/password")
    public String getPassword(){
        return nacosConfig2.getPassword();
    }

    @GetMapping("/name1")
    public String getName1(){
        return nacosConfig3.getName();
    }
}
