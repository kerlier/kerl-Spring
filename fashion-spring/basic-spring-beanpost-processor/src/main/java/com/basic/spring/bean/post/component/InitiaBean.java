package com.basic.spring.bean.post.component;

import org.springframework.beans.factory.InitializingBean;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/5 14:59
 */
//@Component("myOwnComponent")
public class InitiaBean implements InitializingBean {

    public InitiaBean(){
        System.out.println("执行====");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean afterPropertiesSet");
    }

    public void init(){
        System.out.println("初始化init方法");
    }
}
