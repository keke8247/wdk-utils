package com.wdk.util.thread.pro;

/**
 * @Description
 * 电影 公共资源.
 *
 * wait()方法 线程阻塞 释放锁资源 等待其他线程获取锁资源后执行notify()/notifyAll()方法唤醒线程.
 * sleep()方法 线程阻塞 不释放锁资源
 *
 * wait()和notify()/notifyAll() 需要再synchronized锁定的语句块里面使用
 *
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/2/15 10:16
 * @Since version 1.0.0
 */
public class Movie {
    private String pic;

    //设置一个标识位
    //flag --> true 生产者生产电影 消费者等待
    //flag --> false 消费者消费 生产者等待
    private boolean flag = true;

    //生产电影
    public  void play(String pic){

        synchronized (this){
            if(!flag){ //false 消费者在消费电影 生产者等待.
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(500); //生产耗时500ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.pic = pic;
            System.out.println("生产了:"+pic);

            //生产完成 通知消费者消费
            this.notifyAll();
            flag = false;
        }
    }

    public synchronized void watch(){
        if(flag){ //生产者在生产电影 消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(200); //观看耗时200ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("观看了:"+pic);

        //观看完毕 唤醒生产者
        this.notifyAll();

        //修改标识位
        flag = true;
    }
}
