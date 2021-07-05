package com.basic.spring.bean.post.instantiationAware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/5 15:49
 */
@RestController
public class My2Controller {

    @Autowired
    @Qualifier("myInstantiationBean")
    private MyInstantiationBean myInstantiationBean;

    @GetMapping("/get")
    public void print(){
        System.out.println(myInstantiationBean.getPassword());
    }
}
