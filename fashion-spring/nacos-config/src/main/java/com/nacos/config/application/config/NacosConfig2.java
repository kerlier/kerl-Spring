package com.nacos.config.application.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySources;
import org.springframework.stereotype.Component;

/**
 * @Author: yangyuguang
 * @Date: 2021/5/12 8:46
 */
@Component
@NacosPropertySource(dataId = "nacos-config",groupId = "test2", autoRefreshed = true)
public class NacosConfig2 {

    @NacosValue(value = "${name}", autoRefreshed = true)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
