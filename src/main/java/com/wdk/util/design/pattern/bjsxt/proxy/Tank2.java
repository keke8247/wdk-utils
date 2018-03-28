package com.wdk.util.design.pattern.bjsxt.proxy;

/**
 * @Description
 * 通过继承实现 记录 tank的move方法运行时间
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/27 16:52
 * @Since version 1.0.0
 */
public class Tank2 extends Tank{
    public void move(){
        Long start = System.currentTimeMillis();
        super.move();
        Long end = System.currentTimeMillis();

        System.out.println("time:"+(end-start));
    }
}
