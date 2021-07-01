package com.fashion.spring.callable;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/1 15:49
 */
public class ReturnMain {

    private static String str = "123";

    public static void main(String[] args) {
        System.out.println(str);

        System.out.println(getStr("str"));

        System.out.println(str);

    }

    public static String getStr(String string){
        return str = string;
    }
}
