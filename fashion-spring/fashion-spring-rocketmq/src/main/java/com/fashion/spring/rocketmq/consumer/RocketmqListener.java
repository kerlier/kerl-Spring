package com.fashion.spring.rocketmq.consumer;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/24 14:41
 */
@Component
@RocketMQMessageListener(topic = "TopicTest1234",selectorExpression = "*",consumerGroup = "test-group")
public class RocketmqListener implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt messageExt) {
        byte[] body = messageExt.getBody();
        String message = new String(body);
        System.out.println("收到消息:"+ message +", msgId:"+ messageExt.getMsgId());
    }
}
