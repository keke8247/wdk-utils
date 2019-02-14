package com.wdk.util.thread;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/8 9:24
 * @Since version 1.0.0
 */
public class TestSync2 implements Runnable{
    int b = 100;

    synchronized void m1() throws InterruptedException {
        b = 1000;
//        Thread.sleep(500);

        System.out.println("b ="+b);
    }

    synchronized  void m2() throws InterruptedException {
//        Thread.sleep(250);
        b = 2000;
    }

    public static void main(String[] args) throws InterruptedException {
        TestSync2 ts = new TestSync2();
        Thread t = new Thread(ts);
        t.start();
        ts.m2();

        System.out.println("main thread b="+ts.b);
    }

    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
