package com.wdk.util.design.pattern.strategy;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/12 13:56
 * @Since version 1.0.0
 */
public class DataSorter {
    private int [] a ;

    public DataSorter(int[] a) {
        this.a = a;
    }

    public void sort(){
        for(int i=0;i<a.length-1;i++){
            if(a[i] >a[i+1]){
                int tmp = a[i];
                a[i] = a[i+1];
                a[i+1] = tmp;
            }
        }
    }

    public void println(){
        for (int i=0;i<a.length;i++){
            System.out.printf("a[%d] = %d%n",i,a[i]);
        }
    }
}
