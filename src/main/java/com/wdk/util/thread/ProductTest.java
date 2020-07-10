package com.wdk.util.thread;

/**
 * @Description
 * @Author rdkj
 * @CreatTime 2020/5/25 16:20
 * @Since version 1.0.0
 */
public class ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer producer = new Producer(clerk);
        producer.setName("生产者1:");

        Consumer consumer = new Consumer(clerk);
        consumer.setName("消费者1");

        producer.start();
        consumer.start();
    }
}

class Clerk{
    private int productNum = 0;

    public synchronized void produceProduct() {
        if(productNum<20){
            productNum ++;
            System.out.println(Thread.currentThread().getName()+":开始生产第:"+productNum+"个产品");
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consumeProduct() {
        if(productNum>0){
            System.out.println(Thread.currentThread().getName()+":开始消费第:"+productNum+"个产品");
            productNum --;
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer extends Thread{
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }
    }
}

class Consumer extends Thread{
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }
}

