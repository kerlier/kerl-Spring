package com.fashion.spring.reflection;

import org.springframework.util.ReflectionUtils;

public class ReflectionApplication {

    public static void main(String[] args) {
        AnnotationUser annotationUser = new AnnotationUser();
        KerlValue annotation = annotationUser.getClass().getAnnotation(KerlValue.class);
        System.out.println(annotation.value());

    }
}
