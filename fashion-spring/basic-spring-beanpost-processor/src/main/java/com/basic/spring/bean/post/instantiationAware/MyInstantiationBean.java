package com.basic.spring.bean.post.instantiationAware;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/5 15:43
 */
@Component("myInstantiationBean")
public class MyInstantiationBean {

    private String name = "10";

    @Value("${password}")
    private String password;

    public MyInstantiationBean(){
        System.out.println("构造方法MyInstantiationBean");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
