package com.CK;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static void printListNode(ListNode listNode) {
        while (listNode.next != null) {
            System.out.print(listNode.val + "->");
            listNode = listNode.next;
        }
        System.out.print(listNode.val + ".\n");
    }
}