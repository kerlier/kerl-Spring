package com.basic.spring.bean.post.beanNameAware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/5 17:30
 */
@Component(value = "test")
public class MyOwnBeanNameAware implements BeanNameAware {

    @Override
    public void setBeanName(String name) {
        System.out.println("MyOwnBeanNameAware: " + name);
    }
}
