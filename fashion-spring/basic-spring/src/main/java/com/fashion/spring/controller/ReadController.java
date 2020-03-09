package com.fashion.spring.controller;


import com.fashion.spring.service.PropertyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/info")
public class ReadController {

    @Resource
    private PropertyService propertyService;

    @RequestMapping("/getApplicationName")
    public String getApplicationName(){
        return propertyService.getApplicationName();
    }
}
