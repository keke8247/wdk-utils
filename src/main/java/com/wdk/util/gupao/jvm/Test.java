package com.wdk.util.gupao.jvm;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/2/14 9:13
 * @Since version 1.0.0
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(Integer.toBinaryString(-16));

        for(int i=0;i<100;i++){
            if(check(i)){
                System.out.printf("%d:是2的整次幂~~~~~~~~~~~~~~\n",i);
            }else{
                System.out.printf("%d:不是2的整次幂--------------\n",i);
            }
        }

    }

    public static boolean check(int num){
        if(num < 2){
            return false;
        }
        int tmp = num&(num-1);
        return  tmp == 0 ? true : false;
    }
}

