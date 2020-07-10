package com.wdk.scala.leetcode

import java.util

import scala.collection.mutable.ArrayBuffer

/**
  * @Description:
  * @Author:wang_dk
  * @Date:2020-06-25 18:04
  * @Version: v1.0
  **/
object LeetCodeTest {


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
        nums.map(x => {
            nums.filter(y=> y<x).size
        })
    }


    //目标数组 target 最初为空
    //按从左到右的顺序依次读取 nums[i] 和 index[i]，在 target 数组中的下标 index[i] 处插入值 nums[i] 。
    //重复上一步，直到在 nums 和 index 中都没有要读取的元素。
    def createTargetArray(nums: Array[Int], index: Array[Int]): Array[Int] = {
        var target = new java.util.ArrayList[Int]()

        for(i <- 0 to index.length-1){
            target.add(index(i), nums(i))
        }
        import scala.collection.JavaConverters._
        target.asScala.toArray
    }

    //考虑每对相邻的两个元素 [freq, val] = [nums[2*i], nums[2*i+1]] （其中 i >= 0 ），
    // 每一对都表示解压后子列表中有 freq 个值为 val 的元素，你需要从左到右连接所有子列表以生成解压后的列表。
    /**
      * 输入：nums = [1,2,3,4]
        输出：[2,4,4,4]
        解释：第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。
        第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]。
        最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]。
      * */
    def decompressRLElist(nums: Array[Int]): Array[Int] = {
        val res:ArrayBuffer[Int] = new ArrayBuffer[Int]()
        for(i <- Range(0,nums.length-1,2)){
            for(j <- 0 until nums(i)){
                res.append(nums(i+1))
            }
        }
        res.toArray
    }

    //请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
    /*
    *   输入: head = [4,5,1,9], node = 5
        输出: [4,1,9]
        解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
    * */
    //题解:题目意思是删除当前的node节点，只需要把next的节点数据拷贝当前的node节点就可以，相当于跳过当前节点了。
    def deleteNode(node: ListNode): Unit = {
        node.x = node.next.x
        node.next = node.next.next
    }

    //题目:桌上有 n 堆力扣币，每堆的数量保存在数组 coins 中。我们每次可以选择任意一堆，拿走其中的一枚或者两枚，求拿完所有力扣币的最少次数。
    //题解: 能被2整除 就一次拿2个 n/2 次拿完.不能被2整除 最后一次拿1个  拿n/2+1次
    def minCount(coins: Array[Int]): Int = {
        var res = 0
        for(i <- coins){
            if(i % 2 == 0){
                res += i/2
            }else{
                res += (i/2+1)
            }
        }
        res
    }

    /*
    *   给你两个整数数组 startTime（开始时间）和 endTime（结束时间），并指定一个整数 queryTime 作为查询时间。
        已知，第 i 名学生在 startTime[i] 时开始写作业并于 endTime[i] 时完成作业。
        请返回在查询时间 queryTime 时正在做作业的学生人数。形式上，返回能够使 queryTime 处于区间 [startTime[i], endTime[i]]（含）的学生人数。
    * */
    //题解:
    // 1.使用zip 函数.把两个数组合并成一个 tuple(startTime,endTime)格式的数组,
    // 2.使用filter过滤

    def busyStudent(startTime: Array[Int], endTime: Array[Int], queryTime: Int): Int = {
        val tuples: Array[(Int, Int)] = startTime.zip(endTime)
        val array: Array[(Int, Int)] = tuples.filter(item=>(item._1<= queryTime && item._2 >= queryTime))
        array.size
    }

    /*
    * 题目:
    * 平面上有 n 个点，点的位置用整数坐标表示 points[i] = [xi, yi]。请你计算访问所有这些点需要的最小时间（以秒为单位）。
        你可以按照下面的规则在平面上移动：
        每一秒沿水平或者竖直方向移动一个单位长度，或者跨过对角线（可以看作在一秒内向水平和竖直方向各移动一个单位长度）。
        必须按照数组中出现的顺序来访问这些点。
        输入：points = [[1,1],[3,4],[-1,0]]
        输出：7
        解释：一条最佳的访问路径是： [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]
        从 [1,1] 到 [3,4] 需要 3 秒
        从 [3,4] 到 [-1,0] 需要 4 秒
        一共需要 7 秒
    * */
    //题解: 遍历素组获取节点坐标.计算当前坐标 到下一坐标的位移步骤. 由于可以 斜着走 所以 最小步数为 min(x1-x2,y1-y2)
    def minTimeToVisitAllPoints(points: Array[Array[Int]]): Int = {
        val pointZip: Array[(Array[Int], Int)] = points.zipWithIndex
        val intses: Array[Array[Int]] = pointZip.collect {
            case (point, index) if index < pointZip.length - 1 => {
                Array(Math.max(Math.abs(point(0) - points(index + 1)(0)), Math.abs(point(1) - points(index + 1)(1))))
            }
        }
        intses.flatten.sum
//        val ints: ArrayBuffer[Int] = new ArrayBuffer[Int]()
//        for (i <- 0 until points.length-1){
//            ints.append(Math.max(Math.abs(points(i)(0) - points(i + 1)(0)), Math.abs(points(i)(1) - points(i + 1)(1))))
//        }
//
//        ints.toArray.sum
    }

    //题目:给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
    //题解:先获取链表的长度. 再转换数字
    def getDecimalValue(head: ListNode): Int = {
//        var length = 0
//        var tmp = head
//        while (tmp.next!=null){
//            tmp = tmp.next
//            length += 1
//        }
//        var tmp2 = head
//        var res = 0
//        while (tmp2.next != null){
//            res += (tmp2.x * Math.pow(2,length)).toInt
//            tmp2 = tmp2.next
//        }
//        res += tmp2.x
//        res

        var h = head
        var sum = 0
        while (h != null) {
            sum = sum * 2 + h.x
            h = h.next
        }
        sum

    }

    def main(args: Array[String]): Unit = {
        //        println(smallerNumbersThanCurrent(Array(1,3,2,5)).mkString(","))

        val node: ListNode = new ListNode(1)
        node.next = new ListNode(0)
        node.next.next = new ListNode(1)

        println(getDecimalValue(node))
    }


}

class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x
}
