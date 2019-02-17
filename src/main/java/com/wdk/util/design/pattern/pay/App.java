package com.wdk.util.design.pattern.pay;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext xmlWebApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        xmlWebApplicationContext.start();

        AbstractChinaPay.payMap.get(Constants.WECHAT_PAY).payment(null);
    }
}
