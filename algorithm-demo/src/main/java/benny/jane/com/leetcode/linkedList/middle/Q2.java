package benny.jane.com.leetcode.linkedList.middle;

import com.benny.learning.algorithm.utils.ListNode;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * 19. 删除链表的倒数第 N 个结点
 */
public class Q2 {
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;

        while (n > 0) {
            fast = fast.next;
            n--;
        }

        // TODO 判断条件比较麻烦
        // fast 夺走一步，使得slow落在待删除节点的前一个节点
        if (fast == null) { // n 等于 链表总长度
            return head.next;
        } else {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        // 添加辅助节点
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}


