package com.fashion.spring.rocketmq.producer;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: yangyuguang
 * @Date: 2021/8/17 15:57
 */
@Component
public class MySqlTransactionListener implements TransactionListener {

    private AtomicInteger state = new AtomicInteger(0);

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    private HashSet<String> set =  new HashSet<>();
    /**
     * 一般都是在这执行本地事务
     * @param message
     * @param o
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        String transactionId = message.getTransactionId();
        int andIncrement = state.getAndIncrement();
        int state = andIncrement % 3;
        localTrans.put(transactionId,state);
        LocalTransactionState localState = getState(state);
        if(localState.equals(LocalTransactionState.COMMIT_MESSAGE)){
            set.add(transactionId);
        }
        System.out.println("事务ID:"+ transactionId+",事务状态:"+ localState+", object:" + o);
        return localState;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        String transactionId = messageExt.getTransactionId();
        Integer integer = localTrans.get(transactionId);
        if(integer != null){
            LocalTransactionState state = getState(integer);
            System.out.println("本地检查事务状态: 事务Id:" + transactionId+" 状态:" + state);
            return state;
        }else{
            System.out.println("本地检查事务状态: 事务Id:" + transactionId+" 未知状态");
        }
        return LocalTransactionState.UNKNOW;
    }

    public LocalTransactionState getState(int state){
        switch (state){
            case 0:
               return LocalTransactionState.COMMIT_MESSAGE;
            case 1:
                return LocalTransactionState.ROLLBACK_MESSAGE;
            case 2:
                return LocalTransactionState.UNKNOW;
        }
        return LocalTransactionState.UNKNOW;
    }

    public HashSet<String> getSet(){
        return set;
    }
}
