package com.wdk.util.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * 编写一个程序 开启3个线程,这三个线程分别为A,B,C,每个线程将自己的ID 打印10,要求输出结果按顺序显示.
 * 例如 ABCABCABC.....
 * @Author:wang_dk
 * @Date:2020-05-30 20:02
 * @Version: v1.0
 **/

public class TestABCAlternate {
    public static void main(String[] args) {
        AlternateDemo alternateDemo = new AlternateDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=20;i++){
                    alternateDemo.loopA(i);
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=20;i++){
                    alternateDemo.loopB(i);
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=20;i++){
                    alternateDemo.loopC(i);
                }
            }
        },"C").start();

    }
}

class AlternateDemo{

    //声明一个锁
    private Lock lock = new ReentrantLock();

    //做一个线程标志位
    private int threadNum = 1;

    //声明3个Condition 用于线程间的通信
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void loopA(int forNum){
        lock.lock();
        try {
            if(threadNum != 1){ //如果不是当前A线程的执行时间 .等待
                condition1.await();
            }

            //如果是当前A线程的执行时间  打印A
            System.out.println(Thread.currentThread().getName()+"\t"+forNum);

            //A打印完了 把线程标志位置为2
            threadNum = 2;
            condition2.signal();//唤醒 线程2

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void loopB(int forNum){
        lock.lock();
        try {
            if(threadNum != 2){ //如果不是当前B线程的执行时间 .等待
                condition2.await();
            }

            //如果是当前线程的执行时间  打印B
            System.out.println(Thread.currentThread().getName()+"\t"+forNum);

            //B打印完了 把线程标志位置为2
            threadNum = 3;
            condition3.signal();//唤醒 线程2

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void loopC(int forNum){
        lock.lock();
        try {
            if(threadNum != 3){ //如果不是当前C线程的执行时间 .等待
                condition3.await();
            }

            //如果是当前C线程的执行时间  打印C
            System.out.println(Thread.currentThread().getName()+"\t"+forNum);

            //C打印完了 把线程标志位置为1
            threadNum = 1;
            condition1.signal();//唤醒 线程1

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
