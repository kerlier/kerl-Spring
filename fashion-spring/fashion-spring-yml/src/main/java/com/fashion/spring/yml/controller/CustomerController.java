package com.fashion.spring.yml.controller;

import com.fashion.spring.yml.config.Customer;
import com.fashion.spring.yml.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Kerl
 * @Date: 2021/5/19 10:21
 */
@RestController
public class CustomerController {

    @Autowired
    private SecurityConfig securityConfig;

    @GetMapping("/getCustomer")
    public List<Customer> getCustomer(){
        return securityConfig.getCustomer();
    }
}
