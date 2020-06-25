package com.wdk.scala.leetcode

/**
  * @Description:
  * @Author:wang_dk
  * @Date:2020-06-25 18:04
  * @Version: v1.0
  **/
object LeetCodeTest {
    def main(args: Array[String]): Unit = {
    }

    //给你一个整数数组 nums，请你返回其中位数为 偶数 的数字的个数。
    def findNumbers(nums: Array[Int]): Int = {
        var number : Int = 0
        nums.map(num => {
            if (num.toString.length % 2 == 0){
                number += 1
            }
        })

        number
    }

    //给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
    def smallerNumbersThanCurrent(nums: Array[Int]): Array[Int] = {


        nums
    }
}
