package com.wdk.util.design.pattern.strategy;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/12 13:56
 * @Since version 1.0.0
 */
public class Test {
    static int [] i = {9,1,32,4,5,13};

    public static void main(String[] args) {
        DataSorter ds = new DataSorter(i);

        ds.sort();

        ds.println();
    }
}
