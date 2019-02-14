package com.wdk.util.thread.bank;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/9/5 14:25
 * @Since version 1.0.0
 */
public class PersonA extends Thread{
    public Bank bank;

    public PersonA(Bank bank){
        super();
        this.bank = bank;
    }

    public void run(){
        while (Bank.money>=100){
            bank.counter(100); //每次取款100块

            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
