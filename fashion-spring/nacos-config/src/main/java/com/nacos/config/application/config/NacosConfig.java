package com.nacos.config.application.config;

import org.springframework.stereotype.Component;

/**
 * @Author: yangyuguang
 * @Date: 2021/5/12 8:46
 */
@Component
//@NacosPropertySource(dataId = "nacos-config",groupId = "xfd2", autoRefreshed = true)
public class NacosConfig {

//    @NacosValue(value = "${name}", autoRefreshed = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
