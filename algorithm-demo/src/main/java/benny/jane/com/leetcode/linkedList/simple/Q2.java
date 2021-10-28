package benny.jane.com.leetcode.linkedList.simple;

import com.benny.learning.algorithm.utils.ListNode;

/**
 *
 */
public class Q2 {
    // 考察：链表删除节点的操作
    public ListNode removeElements3(ListNode head, int val) {
        ListNode pre = new ListNode();
        ListNode tail = pre;

        while (head != null) {
            if (head.val != val) {
                tail.next = head;
                tail = tail.next;
            } else {
                // TODO 核心点：等值节点需要处理
                tail.next = null;
            }
            head = head.next;
        }

        return pre.next;
    }

    // 在原链表上进行操作
    public ListNode removeElements2(ListNode head, int val) {
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode tail = pre;
        while (tail.next != null) {
            if (tail.next.val == val) {
                // 跳过节点
                tail.next = tail.next.next;
            } else {
                tail = tail.next;
            }
        }
        return pre.next;
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        // 先处理head节点后的节点
        head.next = removeElements(head.next, val);
        // 最后处理head节点的判断
        return head.val == val ? head.next : head;
    }
}


