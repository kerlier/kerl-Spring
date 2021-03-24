package com.test;

import com.nacos.config.application.controller.Input;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/16 14:41
 */
public class TestReflection {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Input input = new Input("test-aaaa");
        //查找当前的方法
        Method method = ReflectionUtils.findMethod(Input.class, "getName");
        //执行当前的方法
        Object invoke = ReflectionUtils.invokeMethod(method,input);
        System.out.println(invoke);
    }
}
