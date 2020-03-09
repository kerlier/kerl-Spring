package com.fashion.spring.service.impl;

import com.fashion.spring.pojo.ReadProperties;
import com.fashion.spring.service.PropertyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Resource
    private ReadProperties properties;

    public String getApplicationName(){
        return properties.getApplicationName();
    }
}
