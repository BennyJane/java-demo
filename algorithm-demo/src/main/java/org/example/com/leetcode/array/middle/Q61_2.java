package org.example.com.leetcode.array.middle;

import java.util.Stack;

/**
 * 581. 最短无序连续子数组
 * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
 */
public class Q61_2 {
    // 需要考虑重复值
    // 利用栈结构
    public int findUnsortedSubarray(int[] nums) {
        Stack<Integer> stack = new Stack<Integer>();
        int l = nums.length, r = 0;
        // 找到错位的最小值
        for (int i = 0; i < nums.length; i++) {
            // 找到非递增的索引位置，寻找顺序不对的最小值
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                l = Math.min(l, stack.pop());
            }
            stack.push(i);  // 栈存储前序索引
        }
        stack.clear();
        // 找到错位的最大值
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                r = Math.max(r, stack.pop());
            }
            stack.push(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }

    public int findUnsortedSubarray2(int[] nums) {
        Stack<Integer> stack = new Stack<Integer>();
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length; i++) {
            // 找到非递增的索引位置，寻找顺序不对的最小值
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                l = Math.min(l, stack.pop());
                r = Math.max(r, i);
            }
            stack.push(i);  // 栈存储前序索引
        }
        return r - l > 0 ? r - l + 1 : 0;
    }

    public static void main(String[] args) {
        Q61_2 q = new Q61_2();
//        int[] nums = new int[]{2, 6, 4, 8, 10, 9, 15};
        int[] nums = new int[]{1, 2, 3, 4};
//        int[] nums = new int[]{1, 3, 2, 2, 2};
//        int[] nums = new int[]{1, 2, 3, 5, 4};
//        int[] nums = new int[]{2, 1};
        q.findUnsortedSubarray2(nums);
    }
}


