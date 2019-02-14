package com.wdk.util.thread.bank;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/9/5 14:29
 * @Since version 1.0.0
 */
public class TestBank {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();


        Bank bank = new Bank();

        PersonA personA = new PersonA(bank);

        PersonB personB = new PersonB(bank);

        personA.start();
        personB.start();
        System.out.println("总共执行了:"+ (System.currentTimeMillis()-start));
    }
}
