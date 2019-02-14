package com.wdk.util.gupao.Thread;

/**
 * @Description
 * wait() 释放对象锁
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/2/13 15:39
 * @Since version 1.0.0
 */
public class ThreadWait extends Thread{

    private Object lock;

    public ThreadWait(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println("开始执行ThreadWait");

            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束执行ThreadWait");
        }
    }
}
