package com.wdk.util.thread.bank;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/9/5 14:20
 * @Since version 1.0.0
 */
public class Bank {
    public static int money = 1000; //共有一千块

    public void counter(int mon){
        System.out.println("Person A 在柜台取钱"+mon+"元");
        money -= mon;
        System.out.println("现在还剩"+money+"元");
    }

    public void atm(int mon){
        System.out.println("Person B 在ATM取钱"+mon+"元");
        money -= mon;
        System.out.println("现在还剩"+money+"元");
    }
}
