package com.wdk.util.thread;

/**
 * @Description
 * 线程1 线程2 交替打印100 个数字
 * @Author rdkj
 * @CreatTime 2020/5/25 15:43
 * @Since version 1.0.0
 */
public class Number implements Runnable{
    private int num = 1;

    @Override
    public void run() {
        while (true){
            synchronized (this){
                notify();
                if(num <= 100){
                    System.out.println(Thread.currentThread().getName()+":"+num);
                    num ++;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();

    }
}
