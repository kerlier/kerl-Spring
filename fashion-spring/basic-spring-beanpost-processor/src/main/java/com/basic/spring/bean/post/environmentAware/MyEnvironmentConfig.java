package com.basic.spring.bean.post.environmentAware;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/5 17:21
 */
@Configuration
public class MyEnvironmentConfig implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getProperty(String propertyName){
        return environment.getProperty(propertyName);
    }
}
