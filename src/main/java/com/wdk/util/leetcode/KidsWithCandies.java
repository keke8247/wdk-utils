package com.wdk.util.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * 给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。

    对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有 最多 的糖果数目

    输入：candies = [2,3,5,1,3], extraCandies = 3
    输出：[true,true,true,false,true]
    解释：
    孩子 1 有 2 个糖果，如果他得到所有额外的糖果（3个），那么他总共有 5 个糖果，他将成为拥有最多糖果的孩子。
    孩子 2 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
    孩子 3 有 5 个糖果，他已经是拥有最多糖果的孩子。
    孩子 4 有 1 个糖果，即使他得到所有额外的糖果，他也只有 4 个糖果，无法成为拥有糖果最多的孩子。
    孩子 5 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。

 * @Author:wang_dk
 * @Date:2020-05-30 22:11
 * @Version: v1.0
 **/

public class KidsWithCandies {

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        int [] tmp = Arrays.copyOf(candies,candies.length);

        List<Boolean> res = new ArrayList<>();

        Arrays.sort(tmp);
        int max = tmp[tmp.length -1];

        for (int i = 0; i < candies.length; i++) {
            if((candies[i]+extraCandies)>=max){
                res.add(true);
            }else{
                res.add(false);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int [] candies = {2,3,5,1,3};
        List<Boolean> res = kidsWithCandies(candies,3);

        res.forEach(item-> System.out.println(item.toString()));
    }

}
