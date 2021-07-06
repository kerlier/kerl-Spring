package com.basic.spring.bean.post.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/5 14:25
 */
@Component
public class MyOwnBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(Objects.equals(beanName,"myOwnComponent")){
//            MyOwnComponent component = (MyOwnComponent)bean;
//            try{
//                String name = component.getComponent().getName();
//                System.out.println("test: " + name);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            System.out.println("post "+bean.getClass() +" name: "+ component.getComponentName());
            System.out.println("post: " + bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(Objects.equals(beanName,"myOwnComponent")){
//            MyOwnComponent component = (MyOwnComponent)bean;
//            try{
//                String name = component.getComponent().getName();
//                System.out.println("test: " + name);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            System.out.println("before "+bean.getClass() +" name: "+ component.getComponentName());

            System.out.println("before: " + bean.getClass());
        }
        return bean;
    }
}
