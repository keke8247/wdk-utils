package com.wdk.util.data.structure;

import java.util.Random;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/2/12 17:07
 * @Since version 1.0.0
 */
public class Caishuzi {
    private static int  num;
    static {
        num = new Random().nextInt(100);

        System.out.printf("随机数字%d",num);
    }

    static int max = 100;
    static int min = 0;
    static int tmp = 1;
    private static void caishu(int i){
        System.out.println();
        System.out.printf("第%d次猜,i=%d",tmp,i);

        if(i == num){
            System.out.printf("猜对了!i=%d",i);
        }else{
            if(i<num){
                min = i;
            }else{
                max = i;
            }
            i = (min+max)/2;

            tmp++;
            caishu(i);
        }
    }

    public static void main(String[] args) {
//        caishu((max+min)/2);

        String key = "test";

        int h = 0;
        h = (h = key.hashCode()) ^ (h >>> 16);

        System.out.println();
        System.out.println(h);
    }
}
