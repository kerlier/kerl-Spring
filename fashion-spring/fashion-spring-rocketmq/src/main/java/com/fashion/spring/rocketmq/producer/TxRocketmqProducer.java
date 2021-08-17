package com.fashion.spring.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yangyuguang
 * @Date: 2021/8/17 15:38
 */
@Component
public class TxRocketmqProducer {
    private TransactionMQProducer producer;

    @Autowired
    private MySqlTransactionListener mySqlTransactionListener;

    @PostConstruct
    public void init() throws MQClientException {
        producer = new TransactionMQProducer("fashion-tx-mq-producer");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("fashion-tx-mq-thread");
                return thread;
            }
        });

        producer.setExecutorService(threadPoolExecutor);
        producer.setTransactionListener(mySqlTransactionListener);
        producer.start();
    }


    public void send(String message){
        try {
            Message msg =
                    new Message("TopicTest1234", message, "KEY" + message,
                            message.getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.sendMessageInTransaction(msg, null);
            System.out.printf("%s%n", sendResult);

            Thread.sleep(10);
        } catch (MQClientException | UnsupportedEncodingException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
