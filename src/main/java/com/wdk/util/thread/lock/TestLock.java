package com.wdk.util.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * 模拟售票
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/2/15 16:11
 * @Since version 1.0.0
 */
public class TestLock {
    public static void main(String[] args) {
        Ticket1 t = new Ticket1();

        new Thread(t,"Thread1").start();
        new Thread(t,"Thread2").start();
        new Thread(t,"Thread3").start();
    }
}

//不加锁
class Ticket1 implements Runnable{

    private int ticketNums = 100;

    @Override
    public void run() {
        while (true){
            if(ticketNums > 0){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"完成售票,余票为: "+ --ticketNums);
            }else{
                break;
            }
        }
    }
}

//加锁
class Ticket2 implements Runnable{

    private int ticketNums = 100;

    //重入锁
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            lock.lock();
            try{
                if(ticketNums > 0){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"完成售票,余票为: "+ --ticketNums);
                }else{
                    break;
                }
            }finally {
                lock.unlock();
            }
        }
    }
}