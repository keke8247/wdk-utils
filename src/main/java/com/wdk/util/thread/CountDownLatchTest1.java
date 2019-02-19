package com.wdk.util.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest1 implements Runnable{

    private CountDownLatch countDownLatch;

    CountDownLatchTest1(CountDownLatch countDownLatch){
        super();
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"执行! countDownLatch.getCount()="+countDownLatch.getCount());
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        //初始化countDownLatch计数器为5
        CountDownLatch countDownLatch = new CountDownLatch(5);
        //创建相同数量的线程
        for(int i=0;i<5;i++){
            new Thread(new CountDownLatchTest1(countDownLatch),"Thread"+i).start();
        }
        System.out.println("调用countDownLatch.await()阻塞掉main线程,等待子线程执行完成.");

//        countDownLatch.await();

        System.out.println("main线程执行完成");
    }
}
