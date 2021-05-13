package com.fashion.nacos.register.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fashion.nacos.register.api.ITestService;

/**
 * @Author: yangyuguang
 * @Date: 2021/5/13 9:15
 */
@Service
public class TestServiceImpl implements ITestService {
    @Override
    public String getHello() {
        return "TestServiceImpl";
    }
}
