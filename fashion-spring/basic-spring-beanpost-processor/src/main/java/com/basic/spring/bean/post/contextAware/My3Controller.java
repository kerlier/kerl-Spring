package com.basic.spring.bean.post.contextAware;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/5 17:11
 */
@RestController
public class My3Controller {

    @GetMapping("get1")
    public String get(){
        Object myBean = ApplicationContextUtil.getBean("myBean");
        System.out.println(myBean);
        return "suc";
    }
}
