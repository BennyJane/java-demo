package com.benny.learning.algorithm.leetcode.array.simple;

import com.benny.learning.algorithm.utils.ListNode;

/**
 * 2. 两数相加
 * https://leetcode-cn.com/problems/add-two-numbers/
 */
public class Q30 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int x = 0, y = 0;
        int len = 0;
        while (l1 != null) {
            x = l1.val * (int) Math.pow(10L, len);
            l1 = l1.next;
        }
        len = 0;
        while (l2 != null) {
            y = l2.val * (int) Math.pow(10L, len);
            l2 = l2.next;
        }
        int sum = x + y;
        ListNode ans = null;
        ListNode pre = null;
        while (sum > 0) {
            int n = sum % 10;
            ListNode node = new ListNode(n);
            if (pre == null) {
                pre = node;
                ans = node;
            } else {
                pre.next = node;
                pre = node;
            }
            sum %= 10;
        }

        return ans;
    }
}


