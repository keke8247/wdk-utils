package com.wdk.util.gupao;


import org.junit.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/1/18 15:59
 * @Since version 1.0.0
 */
public class TestHelloService {

    public static void main(String[] args) throws InterruptedException, IOException{
        HelloService helloService = new HelloService();

        CountDownLatch latch = new CountDownLatch(1);

        Random random = new Random(10);

        for(int i=0;i<20;i++){
            Thread t = new Thread(()->{
                try {
                    latch.await();
                    Thread.sleep(random.nextInt(1000));
                    helloService.doRequest();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            t.start();
        }
        latch.countDown();
        System.in.read();
    }
}
