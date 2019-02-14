package com.wdk.util.thread;

import static java.lang.Thread.sleep;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/10/12 11:03
 * @Since version 1.0.0
 */
public class Test {
    public static void main(String[] args) {

            Thread thread = new Thread(new Runnable() {
                int j =0;
                @Override
                public void run() {
                    while(j<5){
                        j++;
                        for(int i=0;i<100;i++){
                            System.out.println(i);
                        }

                        System.out.println(Thread.currentThread().getName()+"要睡觉了");
                        try {
                            sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("开始干活了............");
                }
            });
            thread.start();


//            try {
//                System.out.println("要睡觉了");
//                thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

//            new Runnable() {
//                @Override
//                public void run() {
//                    int j=0;
//                    while(j<5){
//                        j++;
//                        for(int i=0;i<1000;i++){
//                            System.out.println(i);
//                        }
//
//                        try {
//                            System.out.println("要睡觉了");
//                            sleep(5000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }.run();

        System.out.println(Thread.currentThread().getName()+"测试是不是主线程睡觉了");

//        for(int i=0;i<10000;i++){
//            System.out.println("主线程在执行....."+i);
//        }

    }
}
