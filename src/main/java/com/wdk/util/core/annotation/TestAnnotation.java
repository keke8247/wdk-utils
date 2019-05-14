package com.wdk.util.core.annotation;

import org.springframework.stereotype.Repository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description
 * 自定义一个注解.
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/5/14 16:25
 * @Since version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface TestAnnotation {
    public int id();
    String description() default "no description";
    String value();
}
