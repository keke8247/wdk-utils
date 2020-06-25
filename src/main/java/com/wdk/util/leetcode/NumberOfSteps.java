package com.wdk.util.leetcode;

/**
 * @Description:
 * @Author:wang_dk
 * @Date:2020-06-25 17:17
 * @Version: v1.0
 **/

public class NumberOfSteps {
    public static int numberOfSteps(int num){
        if(num < 0){
            return -1;
        }

        int nums = 0;

        while(num > 0){
            nums ++;
            if(num % 2 == 0){
                num = num/2;
            }else{
                num = num -1;
            }
        }
        return  nums ;
    }

    public static void main(String[] args) {
        System.out.println(numberOfSteps(123));
    }
}
