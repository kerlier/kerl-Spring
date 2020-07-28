package com.fashion.job.service.impl;

import com.fashion.job.pojo.Person;
import com.fashion.job.service.SecondService;
import org.springframework.stereotype.Service;

@Service
public class SecondServiceImpl implements SecondService {
    @Override
    public Person queryPerson() {
        return new Person("second service");
    }
}
