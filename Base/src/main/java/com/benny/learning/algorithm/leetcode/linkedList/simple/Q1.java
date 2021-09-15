package com.benny.learning.algorithm.leetcode.linkedList.simple;

import com.benny.learning.algorithm.utils.ListNode;

/**
 * 21. 合并两个有序链表
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class Q1 {
    // 需要创建新的头部节点
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);   // 记录头部节点
        ListNode tail = head;   // 尾部节点不断延长
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            //TODO 必须跳掉下一个节点; 比如牢记当前位置
            tail = tail.next;
        }

        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }

        return head;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

}


