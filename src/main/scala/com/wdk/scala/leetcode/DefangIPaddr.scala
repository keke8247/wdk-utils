package com.wdk.scala.leetcode

/**
  * @Description:
  * @Author:wang_dk
  * @Date:2020-06-25 17:54
  * @Version: v1.0
  **/
object DefangIPaddr {

    def main(args: Array[String]): Unit = {
        println(defangIPaddr("1.1.1.1"))
    }

    def defangIPaddr(ip:String) :String= {
        ip.replace(".","[.]")
    }
}
