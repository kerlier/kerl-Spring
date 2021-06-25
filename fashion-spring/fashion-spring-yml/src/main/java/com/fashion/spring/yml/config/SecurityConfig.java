package com.fashion.spring.yml.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author: Kerl
 * @Date: 2021/5/19 10:18
 */
@Configuration
@ConfigurationProperties(prefix = "xfd-customers")
@Data
public class SecurityConfig {
    private List<Customer> customer;
}
