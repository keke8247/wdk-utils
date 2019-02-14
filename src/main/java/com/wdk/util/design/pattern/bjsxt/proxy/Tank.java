package com.wdk.util.design.pattern.bjsxt.proxy;

import java.util.Random;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/27 16:44
 * @Since version 1.0.0
 */
public class Tank implements Moveable {
    public void move(){
        System.out.println("tank moving");
        try {
            Thread.sleep(new Random().nextInt(10000)); //产生一个10000以内的随机数
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
