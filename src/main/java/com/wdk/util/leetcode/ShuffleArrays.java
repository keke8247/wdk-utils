package com.wdk.util.leetcode;

/**
 * @Description:
 * 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。

    请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。

 * @Author:wang_dk
 * @Date:2020-06-10 21:37
 * @Version: v1.0
 **/

public class ShuffleArrays {

    /**
     * @Description:
     * @Date 2020-06-10 21:38
     * @Param nums 待重新排列的数组
     *        n 数组的长度
     * @return
     **/
    public static int[] shuffle(int [] nums,int n){
        int res [] = new int[2*n];

        for(int i=0;i<2*n;i++){
            if(i % 2 == 0 ){
                res[i] = nums[i/2];
            }else{
                res[i] = nums[(n +i/2)];
            }
        }

        return  res;
    }

    public static void main(String[] args) {
        int []nums = {1,2,3,4,4,3,2,1};
        System.out.println(shuffle(nums,4));
    }


}
