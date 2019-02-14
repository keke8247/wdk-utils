package com.wdk.util.thread.sellTicket;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/9/5 11:43
 * @Since version 1.0.0
 */
public class TestStation {
    public static void main(String[] args) {
        Station s1 = new Station("售票口1");
        Station s2 = new Station("售票口2");
        Station s3 = new Station("售票口3");

        s1.start();
        s2.start();
        s3.start();
    }
}
