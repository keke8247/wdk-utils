package com.wdk.util.gupao.Thread;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/2/13 15:43
 * @Since version 1.0.0
 */
public class ThreadNotify extends Thread{
    private Object lock;

    public ThreadNotify(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println("开始执行ThreadNotify");

            lock.notify();

            System.out.println("结束执行ThreadNotify");
        }
    }
}
