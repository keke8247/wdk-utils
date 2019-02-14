package com.wdk.util.gupao.jvm;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/2/14 9:13
 * @Since version 1.0.0
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread1 = new MyThread("thread1");
        MyThread thread2 = new MyThread("thread2");

        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);

        thread1.start();
        thread2.start();

        Thread.sleep(200);

        thread1.stopMethod();
        thread2.stopMethod();
    }



}

class MyThread extends Thread{

    static boolean flag = true;

    MyThread(String threadName){
        super(threadName);
    }

    @Override
    public void run() {
        while(flag){
            System.out.println(Thread.currentThread().getName());
        }
    }

    public void stopMethod(){
        flag =false;
    }
}
