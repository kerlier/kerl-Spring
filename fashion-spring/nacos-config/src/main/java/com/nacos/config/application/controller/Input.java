package com.nacos.config.application.controller;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/16 14:23
 */
public class Input {

    private String name;

    public Input(){

    }

    public Input(String name){
        this.name = name;
    }

    public void setName(){

    }

    public String getName(){
        return this.name;
    }
}
