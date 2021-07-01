package com.fashion.spring.callable;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/1 14:26
 */
public class CallableUtil {

    public static String stringBlankThenExecute(String source, Callable<String> callable){
        if(Objects.isNull(source)||source.isEmpty()){
            try{
                return callable.call();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("string empty and then execute cause an exception");
            }
        }
        return source;
    }

    public static void main(String[] args) {

        String test = stringBlankThenExecute("", () -> {
            return "为空的时候返回";
        });
        System.out.println(test);
    }
}
