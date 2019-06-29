package com.CK;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ListNode[] listNodes = new ListNode[3];
        ListNode l1 = new ListNode(1);
        ListNode l1_1 = new ListNode(4);
        ListNode l1_2 = new ListNode(5);
        l1_1.next = l1_2;
        l1.next = l1_1;

        ListNode l2 = new ListNode(1);
        ListNode l2_1 = new ListNode(3);
        ListNode l2_2 = new ListNode(4);
        l2_1.next = l2_2;
        l2.next = l2_1;

        ListNode l3 = new ListNode(2);
        ListNode l3_1 = new ListNode(6);
        l3.next = l3_1;

        listNodes[0] = l1;
        listNodes[1] = l2;
        listNodes[2] = l3;

        Solution solution = new Solution();
        ListNode.printListNode(solution.mergeKLists(listNodes));
    }
}

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode pointer = head;
        ListNode minPointer = null;
        ArrayList<ListNode> comparator = new ArrayList<ListNode>();
        for (ListNode list : lists) {
            if (list != null) {
                comparator.add(list);
            }
        }

        while (!comparator.isEmpty()){
            int min = Integer.MAX_VALUE;
            for (ListNode listNode : comparator) {
                if (listNode.val < min) {
                    min = listNode.val;
                }
            }

            for (int i = 0; i < comparator.size(); i++){
                if (comparator.get(i).val == min){
                    pointer.next = new ListNode(comparator.get(i).val);
                    pointer = pointer.next;
                    if (comparator.get(i).next == null){
                        comparator.remove(comparator.get(i));
                    }else {
                        comparator.add(comparator.get(i).next);
                        comparator.remove(i);
                    }
                }
            }
        }

        return head.next;
    }
}