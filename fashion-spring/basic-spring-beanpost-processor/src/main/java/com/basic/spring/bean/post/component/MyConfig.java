package com.basic.spring.bean.post.component;

import org.springframework.context.annotation.Bean;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/5 15:37
 */
//@Component
public class MyConfig {

    @Bean(value = "myOwnComponent",initMethod = "init")
    public InitiaBean bean(){
        return new InitiaBean();
    }
}
