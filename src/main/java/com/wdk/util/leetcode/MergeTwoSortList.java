package com.wdk.util.leetcode;

/**
 * @Description
 * 合并两个有序链表
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/12/27 16:01
 * @Since version 1.0.0
 */
public class MergeTwoSortList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }

        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoSortLists(ListNode l1,ListNode l2){
        // maintain an unchanging reference to node ahead of the return node.
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null){
            if(l1.val < l2.val){
                prev.next = l1;
                l1 = l1.next;
            }else{
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 ==null ?l2:l1;

        return prehead.next;

    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(8);
        ListNode node6 = new ListNode(9);
        l1.setNext(node2.setNext(node3.setNext(node4.setNext(node5.setNext(node6)))));

        ListNode l2 = new ListNode(2);
        ListNode node2_2 = new ListNode(5);
        ListNode node3_2 = new ListNode(7);
        ListNode node4_2 = new ListNode(11);
        ListNode node5_2 = new ListNode(18);
        ListNode node6_2 = new ListNode(30);
        l2.setNext(node2_2.setNext(node3_2.setNext(node4_2.setNext(node5_2.setNext(node6_2)))));

        ListNode l3 = new MergeTwoSortList().mergeTwoSortLists(l1,l2);

        while (l3.next != null){
            System.out.println(l3.val);
            l3 = l3.next;
        }


    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public ListNode setNext(ListNode next) {
        this.next = next;
        return this;
    }
}
