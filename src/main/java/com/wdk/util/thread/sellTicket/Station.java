package com.wdk.util.thread.sellTicket;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/9/5 11:38
 * @Since version 1.0.0
 */
public class Station extends Thread{
    static int ticket = 20; //一共有20张票

    Object lock = "lock";

    Station(String name){
        super(name);
    }

    @Override
    public void run(){
        while (ticket>0){ //还有票可售
            synchronized (lock){ //加锁 防止卖出同一张票
                if(ticket>0){
                    System.out.println(getName()+"卖出第"+ticket+"张票!");
                    ticket --;
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("票已售完!");
                }
            }
        }
    }
}
