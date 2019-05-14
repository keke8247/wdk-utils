package com.wdk.util.core.annotation;

import java.lang.reflect.Method;

/**
 * @Description
 * 测试注解的使用方式  把注解用到类上或者方法上
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/5/14 16:26
 * @Since version 1.0.0
 */
@TestAnnotation(id=1,description = "我是描述",value = "class")
public class UserAnnotation {

    @TestAnnotation(id=2,description = "method",value = "class_method")
    public static void testMethod(){
        System.out.println("测试注解使用到方法上....");
    }


    public static void main(String[] args) {
        TestAnnotation annotation = UserAnnotation.class.getAnnotation(TestAnnotation.class);

        if(null != annotation){
            System.out.println("id: "+annotation.id());
            System.out.println("description: "+ annotation.description());
            System.out.println("value: "+ annotation.value());
        }

        Method[] ms = UserAnnotation.class.getMethods();
        for (Method method : ms){
            if((annotation = method.getAnnotation(TestAnnotation.class)) != null){
                System.out.println("method_id: "+annotation.id());
                System.out.println("method_description: "+ annotation.description());
                System.out.println("method_value: "+ annotation.value());
            }
        }

    }
}
