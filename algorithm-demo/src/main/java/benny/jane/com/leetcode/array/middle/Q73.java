package benny.jane.com.leetcode.array.middle;


import com.benny.learning.algorithm.utils.ListNode;

/**
 * 141 环形链表
 */
public class Q73 {
    // 龟兔赛跑： 快慢指针
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }
}


