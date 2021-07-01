package com.fashion.spring.listener;

import com.alibaba.fastjson.JSONObject;
import com.fashion.spring.event.FashionEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/1 10:53
 */
@Component
public class MyOwnListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent fashionEvent) {
        FashionEvent event = (FashionEvent) fashionEvent;
        System.out.println("获取event:"+ JSONObject.toJSONString(event.getSource()));
    }
}
