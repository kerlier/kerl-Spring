package com.fashion.nacos.register.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fashion.nacos.register.api.ITestService;
import com.fashion.nacos.register.api.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: yangyuguang
 * @Date: 2021/5/13 9:15
 */
@Service
public class TestServiceImpl implements ITestService {
    private static Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    public String getHello() {
        return "TestServiceImpl";
    }

    @Override
    public UserDTO getUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("test");
        userDTO.setUsername("tests");
        LOGGER.info("提供者");
        return userDTO;
    }
}
