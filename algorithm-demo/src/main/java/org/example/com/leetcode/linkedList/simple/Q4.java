package org.example.com.leetcode.linkedList.simple;


import org.example.com.utils.ListNode;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 * 234. 回文链表
 */
public class Q4 {
    // 使用栈结构解决
    // 使用递归解决： TODO 结合PYTHON 很经典（闭包=结构）
    private ListNode frontPointer;

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }

    // 快慢指针
    public boolean isPalindrome2(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾部节点，并反转后半部分的链表
        ListNode firstHalfEnd = findEndOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否为回文
        boolean ans = true;
        ListNode firstHalfStart = head;
        while (secondHalfStart != null) {
            if (firstHalfStart.val != secondHalfStart.val) {
                ans = false;
                break;
            }
            firstHalfStart = firstHalfStart.next;
            secondHalfStart = secondHalfStart.next;
        }

        // 还原链表，并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);

        return ans;
    }

    // 快慢指针找到前一半的终点
    private ListNode findEndOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // TODO 判断条件是关键
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode previous = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;   // 保留后续节点
            cur.next = previous;
            previous = cur;
            cur = next;
        }
        return previous;
    }
}


