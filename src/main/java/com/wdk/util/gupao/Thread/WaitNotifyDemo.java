package com.wdk.util.gupao.Thread;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/2/13 15:44
 * @Since version 1.0.0
 */
public class WaitNotifyDemo {
    public static void main(String[] args) {
        Object lock = new Object();

        ThreadWait threadWait = new ThreadWait(lock);

        threadWait.start();

        ThreadNotify threadNotify = new ThreadNotify(lock);

        threadNotify.start();
    }
}
