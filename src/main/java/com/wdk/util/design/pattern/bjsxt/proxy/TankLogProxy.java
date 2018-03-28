package com.wdk.util.design.pattern.bjsxt.proxy;

/**
 * @Description
 * 通过聚合的方式 实现 记录tank的move方法前后加入日志
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/27 16:54
 * @Since version 1.0.0
 */
public class TankLogProxy implements Moveable{
    private Moveable t;

    public TankLogProxy(Moveable t) {
        this.t = t;
    }

    public void move() throws InterruptedException {
        System.out.println("tank start....");
        t.move();
        System.out.println("tank stop....");
    }
}
