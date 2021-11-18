package org.exmaple.com.leetcode.linkedList.simple;

import com.benny.learning.algorithm.utils.ListNode;

import java.util.Stack;

public class Q3 {
    public ListNode reverseList1(ListNode head) {
        ListNode ans = new ListNode();
        ListNode tail = ans;
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            // FIXME 错误方式： stack.push(head); 这将保存整个链表，而不是独立的单个节点
            stack.push(new ListNode(head.val));
            head = head.next;
        }
        while (!stack.isEmpty()) {
            tail.next = stack.pop();
            tail = tail.next;
        }

        return ans.next;
    }


    // 操作链表本身
    public ListNode reverseList2(ListNode head) {
        ListNode ans = null;
        ListNode ori = head;
        while (ori != null) {
            ListNode temp = ori.next;   // 保存原始链表的下一个位置
            // 调转链表方向，同时更新ans指针位置
            ori.next = ans;
            ans = ori;
            ori = temp;
        }

        return ans;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        Q3 q = new Q3();
        q.reverseList(head);

    }
}


