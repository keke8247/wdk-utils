package com.wdk.util.design.pattern.factory.two;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/6 11:37
 * @Since version 1.0.0
 */
public class SendSmsFactory implements Provider{
    public Sender produce() {
        return new SmsSender();
    }
}
