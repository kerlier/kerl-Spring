package com.fashion.spring.service.impl;

import com.fashion.spring.mapper.db2.CustomerMapper;
import com.fashion.spring.pojo.Customer;
import com.fashion.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer findCustomerById(Integer id) {
        return customerMapper.findUserById(id);
    }
}
