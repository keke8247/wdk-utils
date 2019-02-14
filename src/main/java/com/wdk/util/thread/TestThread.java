package com.wdk.util.thread;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/10/12 11:45
 * @Since version 1.0.0
 */
public class TestThread extends Thread{
    public void run(){
        System.out.println("准备睡觉3秒");
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("睡醒了");
    }
}
