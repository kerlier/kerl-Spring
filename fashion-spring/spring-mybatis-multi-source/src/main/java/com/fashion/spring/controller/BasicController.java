package com.fashion.spring.controller;

import com.fashion.spring.pojo.Customer;
import com.fashion.spring.pojo.User;
import com.fashion.spring.service.CustomerService;
import com.fashion.spring.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BasicController {

    @Resource
    private UserService userService;

    @Resource
    private CustomerService customerService;

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET,produces="application/json")
    public User findUser(@PathVariable Integer id){
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/customer/{id}",method = RequestMethod.GET,produces="application/json")
    public Customer findCustomer(@PathVariable Integer id){

        return customerService.findCustomerById(id);
    }
}
