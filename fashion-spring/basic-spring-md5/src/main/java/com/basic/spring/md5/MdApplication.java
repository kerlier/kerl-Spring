package com.basic.spring.md5;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/5 10:10
 */
public class MdApplication {
    public static void main(String[] args) {
        //7eca689f0d3389d9dea66ae112e5cfd7
        String str = MD5.getInstance().getMD5String("你好");
        System.out.println(str);
    }
}
