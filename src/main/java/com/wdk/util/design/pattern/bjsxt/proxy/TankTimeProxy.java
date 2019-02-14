package com.wdk.util.design.pattern.bjsxt.proxy;

/**
 * @Description
 * 通过聚合的方式 实现 记录tank的move方法执行时间
 * 聚合优于继承,聚合可以灵活组装
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/27 16:54
 * @Since version 1.0.0
 */
public class TankTimeProxy implements Moveable{
    private Moveable t;

    public TankTimeProxy(Moveable t) {
        this.t = t;
    }

    public void move() throws InterruptedException {
        Long start = System.currentTimeMillis();
        t.move();
        Long end = System.currentTimeMillis();
        System.out.println("time:"+(end-start));
    }

}
