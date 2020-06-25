package com.wdk.scala.leetcode

/**
  * @Description:
  * @Author:wang_dk
  * @Date:2020-06-25 17:22
  * @Version: v1.0
  **/
object NumberOfSteps {

    def main(args: Array[String]): Unit = {
        println(numberOfSteps(14))
    }

    def numberOfSteps(num : Int): Int ={
        if(num < 0){
            return -1
        }

        var numeric = num
        var nums = 0

        while (numeric > 0){
            nums += 1
            if(numeric % 2 == 0){
                numeric = numeric / 2
            }else{
                numeric = numeric -1
            }
        }
        nums
    }

}
