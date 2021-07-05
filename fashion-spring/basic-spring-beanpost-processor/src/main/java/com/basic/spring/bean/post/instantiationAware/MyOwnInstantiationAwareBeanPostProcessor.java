package com.basic.spring.bean.post.instantiationAware;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.Objects;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/5 15:41
 */
@Component
public class MyOwnInstantiationAwareBeanPostProcessor
        implements InstantiationAwareBeanPostProcessor {
    /**
     * 此时bean还没有初始化,可以在这里将原来的bean进行替换，比如这里将bean中的属性进行了替换
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
//        if(Objects.equals(beanName,"myInstantiationBean")){
//            System.out.println("执行postProcessBeforeInstantiation方法");
//            MyInstantiationBean myInstantiationBean = new MyInstantiationBean();
//            myInstantiationBean.setName("teststetset");
//            return myInstantiationBean;
//        }
        return null;
    }

    /**
     * return true的话，才会接着向下执行
     * return false,这个bean不会被正常初始化
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if(Objects.equals(beanName,"myInstantiationBean")) {
            System.out.println("在bean初始化之后执行，这时候bean已经有了");
        }
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        if(Objects.equals(beanName,"myInstantiationBean")) {
            System.out.println("postProcessPropertyValues");
        }
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(Objects.equals(beanName,"myInstantiationBean")) {
            System.out.println("postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(Objects.equals(beanName,"myInstantiationBean")) {
            System.out.println("postProcessAfterInitialization");
        }
        return bean;
    }
}
