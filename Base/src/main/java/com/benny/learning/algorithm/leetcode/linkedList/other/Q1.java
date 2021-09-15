package com.benny.learning.algorithm.leetcode.linkedList.other;

import com.benny.learning.algorithm.utils.ListNode;

/**
 * 回文单链表
 * 解法1： 使用栈，遍历遇到相等则弹出； 需要先将所有节点放入栈中
 * 解法2： 快慢指针 快指针一次走两步，慢指针一次走一步
 *      注意事项：实现快指针到达末尾后，慢指针位于不同位置（恰好中间位置，中间位置前一位，中间位置后一位，分奇偶数讨论）
 *
 *  解法3： 结合快慢指针以及额外空间1，使用后半段逆序方式，最后还原链表结构
 */
public class Q1 {

}
