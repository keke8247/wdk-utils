package com.wdk.util.leetcode;

/**
 * @Description:
 * @Author:wang_dk
 * @Date:2020-05-30 22:24
 * @Version: v1.0
 **/

public class DeleteNode {
    public static void deleteNode(MyListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

class MyListNode {
     int val;
    MyListNode next;
    MyListNode(int x) { val = x; }
}
