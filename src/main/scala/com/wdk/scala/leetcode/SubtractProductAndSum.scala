package com.wdk.scala.leetcode

/**
  * @Description:
  *              给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
  * @Author:wang_dk
  * @Date:2020-06-25 17:38
  * @Version: v1.0
  **/
object SubtractProductAndSum {
    def main(args: Array[String]): Unit = {
        println(subtractProductAndSum(1234))
    }

    def subtractProductAndSum(n : Int): Int ={
        //数字拆分成可迭代的集合
        val nums=n.toString.map(_.asDigit)

        // nums.product 集合内所有数字相乘
        // nums.sum 集合内所有数字相加
        nums.product-nums.sum
    }
}
