package com.fashion.clickhouse.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/31 9:25
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource.click")
public class JdbcParamConfig {

    private String driverClass;

    private String url;

    private Integer initialSize;

    private Integer maxActive;

    private Integer minIdle;

    private Integer maxWait;

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }
}
