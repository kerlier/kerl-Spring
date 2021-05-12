package com.nacos.config.application.config;

import org.springframework.stereotype.Component;

/**
 * @Author: yangyuguang
 * @Date: 2021/5/12 8:46
 */
@Component
//@NacosPropertySource(dataId = "nacos-config-2", autoRefreshed = true)
public class NacosConfig2 {

//    @NacosValue(value = "${password}", autoRefreshed = true)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
