package com.wdk.util.wechat;

/**
 * @Description:
 * @Author:wang_dk
 * @Date:2020-06-13 8:24
 * @Version: v1.0
 **/

public class TestThreadLocal {
    private static ThreadLocal<Person> threadLocal  = new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+":"+threadLocal .get());
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Person person = new Person();
                threadLocal .set(person);
                System.out.println(Thread.currentThread().getName()+":"+threadLocal .get().name);
            }
        },"t2").start();
    }
}

class Person{
    String name = "zhangsan";
}