package com.test;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/16 14:18
 */
public class TestInterface {

    public static void main(String[] args) {
    }

    public static InputInterface create(){
        return input -> input.setName();
    }
}

