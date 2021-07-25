package com.nacos.config.application.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/8 16:26
 */
public class ConfigMain {

    public static void main(String[] args) {
        //连接到目标服务的地址
        //指定dataid、 groupid
        String serverAddr="localhost:8848";
        String dataId="nacos-config";
        String groupId="xfd2";
        Properties properties=new Properties();
        properties.put("serverAddr",serverAddr);
        properties.put("namespace","f809bd6f-e655-4943-b842-4b1b8e0c5157");
        try {
            //ConfigService-> NacosConfigService
            ConfigService configService= NacosFactory.createConfigService(properties);
            String content=configService.getConfig(dataId,groupId,3000);
            System.out.println(content);
            configService.addListener(dataId, groupId, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }
                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("configInfo:"+configInfo);
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }

    }
}
