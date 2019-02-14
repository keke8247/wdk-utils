package com.wdk.util.leetcode;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/9/12 9:34
 * @Since version 1.0.0
 */
public class Solution {

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        void setNext(ListNode next){
            this.next = next;
        }
    }

    /*
    * 此题考的是 内存地址引用指向.
    * */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);

        node2.setNext(node3);
        node1.setNext(node2);


        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(4);

        node5.setNext(node6);
        node4.setNext(node5);

        ListNode result = addTwoNumbers(node1,node4);
    }
}
