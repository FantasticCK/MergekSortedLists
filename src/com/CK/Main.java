package com.CK;

import java.util.*;

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

class Solution4 {
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

        while (!comparator.isEmpty()) {
            int min = Integer.MAX_VALUE;
            for (ListNode listNode : comparator) {
                if (listNode.val < min) {
                    min = listNode.val;
                }
            }

            for (int i = 0; i < comparator.size(); i++) {
                if (comparator.get(i).val == min) {
                    pointer.next = new ListNode(comparator.get(i).val);
                    pointer = pointer.next;
                    if (comparator.get(i).next == null) {
                        comparator.remove(comparator.get(i));
                    } else {
                        comparator.add(comparator.get(i).next);
                        comparator.remove(i);
                    }
                }
            }
        }

        return head.next;
    }
}


// Divide and Conquer
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return sort(lists, 0, lists.length - 1);
    }

    private ListNode sort(ListNode[] lists, int lo, int hi) {
        if (lo >= hi) return lists[lo];
        int mid = lo + (hi - lo) / 2;
        ListNode l1 = sort(lists, lo, mid);
        ListNode l2 = sort(lists, mid + 1, hi);
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        }
        l2.next = merge(l1, l2.next);
        return l2;
    }
}

//Heap
class Solution2 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        Queue<ListNode> heap = new PriorityQueue<ListNode>((l1, l2) -> l1.val - l2.val);
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode head = heap.poll();
            tail.next = head;
            tail = head;
            if (head.next != null) {
                heap.add(head.next);
            }
        }
        return dummy.next;
    }
}

class Solution3 {
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int len = lists.length;                 //no extra memory, decrease end of lists each time when merge two
        while (len != 1) {
            for (int i = 0; i < len / 2; i++) {
                lists[i] = mergeTwo(lists[i * 2], lists[i * 2 + 1]);
            }
            if (len % 2 == 1) {                   //move the last one
                lists[len / 2] = lists[len - 1];
            }
            len = (len + 1) / 2;                    //decrease to half
        }
        return lists[0];
    }

    private ListNode mergeTwo(ListNode node1, ListNode node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (node1 != null || node2 != null) {
            if (node2 == null || node1 != null && node1.val < node2.val) {
                curr.next = node1;
                node1 = node1.next;
            } else {
                curr.next = node2;
                node2 = node2.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }
}