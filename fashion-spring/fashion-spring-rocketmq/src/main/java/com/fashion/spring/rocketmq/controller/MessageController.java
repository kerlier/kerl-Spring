package com.fashion.spring.rocketmq.controller;

import com.fashion.spring.rocketmq.producer.RocketmqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/31 17:06
 */
@RestController
public class MessageController {

    @Autowired
    private RocketmqProducer rocketmqProducer;

    @GetMapping("/send")
    public String sendMsg(){
       rocketmqProducer.sendMessage("测试消息");
       return "success";
    }


    @GetMapping("/asyncSend")
    public String sendAsyncMsg(){
        rocketmqProducer.sendAsyncMessage("异步测试消息");
        return "success";
    }
}
