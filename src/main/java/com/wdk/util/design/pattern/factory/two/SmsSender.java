package com.wdk.util.design.pattern.factory.two;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/6 11:42
 * @Since version 1.0.0
 */
public class SmsSender implements Sender{
    public void sendMsg() {
        System.out.println("this is smsMsg");
    }
}
