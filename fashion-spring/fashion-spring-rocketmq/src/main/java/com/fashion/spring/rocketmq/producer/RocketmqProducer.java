package com.fashion.spring.rocketmq.producer;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/24 14:31
 */
@Component
public class RocketmqProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.producer.send-message-timeout}")
    private Integer timeout;

    /**
     * 发送普通消息
     * @param messageBody
     */
    public void sendMessage(String messageBody){
        rocketMQTemplate.syncSend("topic", MessageBuilder.withPayload(messageBody).build());
    }

    /**
     * 发送异步消息。
     * 在sendCallBack处理成功以及失败的逻辑
     * @param messageBody
     */
    public void sendAsyncMessage(String messageBody){
        rocketMQTemplate.asyncSend("topic", MessageBuilder.withPayload(messageBody).build(),
                new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                String msgId = sendResult.getMsgId();
                System.out.println("msgId:"+ msgId+" 发送成功");
            }

            /**
             * 这里最好的做法是将在数据库中增加发送成功的标志，然后进行轮询。
             * 当成功的时候，将数据库的标志置为已发送，而失败不做更改。继续发送消息。
             * 这里需要消费者做去重的判断，因为一条消息可能会发送多次。
             * @param throwable
             */
            @Override
            public void onException(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });
    }
}
