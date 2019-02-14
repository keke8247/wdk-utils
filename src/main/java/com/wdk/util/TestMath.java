package com.wdk.util;

import java.util.Random;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/11/12 17:09
 * @Since version 1.0.0
 */
public class TestMath {
    public static void main(String[] args) {

        Random r = new Random();

        System.out.println(r.nextDouble());

        System.out.println(r.nextInt(1000));

        System.out.println(Math.round(1.52));

        System.out.println(new Double(Math.floor(2.01)).intValue());


        for(int i=0;i<100;i++){
            System.out.println("i="+i +"  :="+r.nextInt(1000));
        }
    }
}
