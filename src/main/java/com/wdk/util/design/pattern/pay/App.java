package com.wdk.util.design.pattern.pay;

public class App {
    public static void main(String[] args) {
        AbstractChinaPay payment = new AliPay();
        payment.payment(new Object());
    }
}
