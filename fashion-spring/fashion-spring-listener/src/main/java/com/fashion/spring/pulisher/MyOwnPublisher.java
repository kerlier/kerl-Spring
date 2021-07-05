package com.fashion.spring.pulisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/1 10:43
 */
@Component
public class MyOwnPublisher implements ApplicationEventPublisher{

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }

    @Override
    public void publishEvent(Object o) {
        applicationContext.publishEvent(o);
    }
}
