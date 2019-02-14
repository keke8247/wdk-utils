package com.wdk.util.design.pattern.factory.two;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/6 11:44
 * @Since version 1.0.0
 */
public class TestClass {
    public static void main(String[] args) {
        Provider provider = new SendSmsFactory();

        Sender sender = provider.produce();

        sender.sendMsg();
    }

}
