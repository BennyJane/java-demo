package org.exmaple.com.leetcode.linkedList.middle;


import org.exmaple.com.utils.ListNode;

/**
 * 86. 分隔链表
 */
public class Q4 {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode slow = pre;
        while (slow.next != null && slow.next.val < x ) {
            slow = slow.next;
        }
        ListNode fast = slow;
        while (fast != null && fast.next != null) {
            if (fast.next.val < x) {
                ListNode slowNext = slow.next;
                slow.next = fast.next;
                fast.next = fast.next.next;
                fast = fast.next;

                slow = slow.next;
                slow.next = slowNext;
            } else {
                fast = fast.next;
            }
        }

        return pre.next;
    }
}


