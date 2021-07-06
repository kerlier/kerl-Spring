package com.fashion.nacos.register.controller;

import com.alibaba.fastjson.JSONObject;
import com.fashion.nacos.register.api.ITestService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Reference
    private ITestService testService;

    @GetMapping("get")
    public String getUser(){
        return JSONObject.toJSONString(testService.getUser());
    }
}
