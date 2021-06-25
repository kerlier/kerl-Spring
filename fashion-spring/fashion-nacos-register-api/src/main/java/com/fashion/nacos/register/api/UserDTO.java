package com.fashion.nacos.register.api;

import java.io.Serializable;

/**
 * @Author: yangyuguang
 * @Date: 2021/6/10 16:10
 */
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -2L;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
