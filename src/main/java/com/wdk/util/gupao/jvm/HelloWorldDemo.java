package com.wdk.util.gupao.jvm;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/2/12 11:17
 * @Since version 1.0.0
 */
public class HelloWorldDemo {

    //常量(方法区)
    private final int i = 0;

    //静态变量(方法区)
    private static int k = 0;

    //成员变量(堆)
    private Object object = new Object();

    public void methodOne(int i){
        int j = 0; //局部变量(栈帧)
        int sum = i+j;
        Object obj = object;
        long start = System.currentTimeMillis();

        return;
    }
}
