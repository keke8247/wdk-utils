package com.wdk.util.design.pattern.factory.two;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/6 11:43
 * @Since version 1.0.0
 */
public class SendEmailFactory implements Provider{
    public Sender produce() {
        return new EmailSender();
    }
}
