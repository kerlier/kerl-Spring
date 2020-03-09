package com.fashion.spring.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReadProperties {

    @Value("${application.name}")
    private String applicationName;


    public String getApplicationName(){
        return applicationName;
    }
}
