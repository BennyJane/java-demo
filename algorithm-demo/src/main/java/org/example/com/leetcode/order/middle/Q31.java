package org.example.com.leetcode.order.middle;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * 215. 数组中的第K个最大元素
 */
public class Q31 {

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int index = nums.length - k + 1;
        return nums[index];
    }

    // 堆: 其实不需要保存索引
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < k; ++i) {
            queue.offer(new int[]{nums[i], i});
        }

        for (int i = k; i < nums.length; i++) {
            if (nums[i] > queue.peek()[0]) {
                queue.poll();
                queue.offer(new int[] {nums[i], i});
            }
        }
        return queue.peek()[0];
    }

}


