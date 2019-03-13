package com.wdk.util.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
* 浅析CountDownLatch
*
* countDownLatch 可以理解为一个原子计数器,同时只能有一个线程去减这个计数器里面的值.
* 任何调用了这个对象的await()方法的线程都会阻塞到 这个计数器对象的数值被其他线程扣减变为0 再开始运行.
* 扣减计数器数值的方法为 countDown();
*
* 通过一个case来理解 CountDownLatch.
*
* 超市老板等着最后三个顾客买完东西之后 关门歇业.
* */
public class CountDownLatchTest{
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CountDownLatch latch = new CountDownLatch(3);

        ShopConsumer consumer1 = new ShopConsumer(latch,"张三");
        ShopConsumer consumer2 = new ShopConsumer(latch,"李四");
        ShopConsumer consumer3 = new ShopConsumer(latch,"王五");

        Boss boss = new Boss(latch);

        executorService.execute(consumer1);
        executorService.execute(consumer2);
        executorService.execute(consumer3);
        executorService.execute(boss);

        executorService.shutdown();
    }
}

class ShopConsumer implements Runnable{
    private CountDownLatch latch;
    private String name;

    public ShopConsumer(CountDownLatch latch, String name) {
        this.latch = latch;
        this.name = name;
    }

    @Override
    public void run() {
        doShopping();
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name+"购物完成!");
        latch.countDown();
    }

    private void doShopping(){
        System.out.println(name+"正在购物.....");
    }
}

class Boss implements Runnable{

    private CountDownLatch countDownLatch;

    public Boss(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("老板等着打烊.");
            countDownLatch.await();
            closed();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void closed(){
        System.out.println("一天的核查工作完成,关门休息.");
    }
}
