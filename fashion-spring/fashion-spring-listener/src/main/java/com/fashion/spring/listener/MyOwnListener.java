package com.fashion.spring.listener;

import com.alibaba.fastjson.JSONObject;
import com.fashion.spring.event.FashionEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/1 10:53
 */
@Component
public class MyOwnListener implements ApplicationListener<FashionEvent> {

    @Override
    public void onApplicationEvent(FashionEvent fashionEvent) {
        System.out.println("获取event:"+ JSONObject.toJSONString(fashionEvent.getSource()));
    }
}
