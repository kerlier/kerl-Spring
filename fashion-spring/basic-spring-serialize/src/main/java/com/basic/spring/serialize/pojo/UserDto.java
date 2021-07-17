package com.basic.spring.serialize.pojo;

import java.io.Serializable;
public class UserDto implements Serializable {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                '}';
    }
}
