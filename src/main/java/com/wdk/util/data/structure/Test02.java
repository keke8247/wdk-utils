package com.wdk.util.data.structure;

import java.util.Arrays;

/**
 * @Description
 * 二分查找
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/7/2 14:35
 * @Since version 1.0.0
 */
public class Test02 {
    public static void main(String[] args) {
        int arr [] = {2,4,5,1,6,9,8};

        int x = getSort(arr,0,arr.length-1,2);

        System.out.println(x);

        System.out.println(Arrays.toString(arr));

    }

    private static int getSort(int [] arr,int start,int end,int findValue){
        Arrays.sort(arr);
        if(null == arr){
            return -1;
        }

        int i=0;
        while(start<=end){
            ++i;
            System.out.println("第("+i+")次查找");
            int mid = (start+end)/2;
            int midValue = arr[mid];
            if(findValue<midValue){
                end = mid-1;
            }else if(findValue == midValue){
                return mid;
            }else{
                start = mid+1;
            }

        }

        return -1;
    }
}
