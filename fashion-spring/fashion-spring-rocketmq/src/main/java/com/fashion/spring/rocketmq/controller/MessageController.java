package com.fashion.spring.rocketmq.controller;

import com.fashion.spring.rocketmq.producer.RocketmqProducer;
import com.fashion.spring.rocketmq.producer.TxRocketmqProducer;
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

    @Autowired
    private TxRocketmqProducer txRocketmqProducer;

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

    @GetMapping("/txSend")
    public String sendTxMsg(){
        for (int i = 0; i < 100; i++) {
            txRocketmqProducer.send("test");
        }
        return "success";
    }
}
