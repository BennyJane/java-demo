package com.benny.learning.algorithm.leetcode;

import java.util.Stack;

/**
 * 1019. 链表中的下一个更大节点
 * https://leetcode-cn.com/problems/next-greater-node-in-linked-list/
 *
 * 寻找比当前数更大的下一个数，直接使用单调栈
 * 参考文章： https://leetcode-cn.com/problems/next-greater-node-in-linked-list/solution/dan-diao-zhan-on-by-huangyt/
 */
public class Q11 {
    // 栈
    static void wayFirst(ListNode head) {
        int limit = 10000;
        int[] result = new int[limit];  // 默认值全为0
        int[] valueArray = new int[limit];
        Stack<Integer> stack = new Stack<>();

        int index = 0;
        int value;
        while (head != null) {
            value = head.val;   // 获取每个节点的数值
            valueArray[index] = value;  // 将链表数据转为数组
            while (!stack.isEmpty() && value > valueArray[stack.peek()]) {
                result[stack.pop()] = value;
            }
            stack.push(index);
            index++;
            head = head.next;   // 获取下一个节点
        }

        // !! 注意result的长度不是链表的长度
        int[] arr = new int[index];
        for (int i =0; i< index; i++) {
            arr[i] = result[i];
        }

    }

    // 双指针

}

class ListNode {
    public ListNode next;
    int val;
}