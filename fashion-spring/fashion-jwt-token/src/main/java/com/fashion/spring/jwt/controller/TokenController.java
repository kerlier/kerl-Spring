package com.fashion.spring.jwt.controller;

import com.fashion.spring.jwt.config.JwtConfig;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/31 10:43
 */
@RestController
public class TokenController {

    @Resource
    private JwtConfig jwtConfig;

    @GetMapping("/login")
    public Map<String,String> login(@RequestParam("username")String username,
                                    @RequestParam("password")String password){
        Map<String,String> result = new HashMap<>();

        String token = jwtConfig.getToken(username + password);
        if(!StringUtils.isEmpty(token)){
            result.put("token", token);
        }
        result.put("username", username);
        return result;
    }

    @GetMapping("/info")
    public String info(){
        return "info";
    }
}
