package org.example.com.high.interview.quickselect;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 215.数组中的第K个最大元素
 */
public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;

        PriorityQueue<Integer> queue = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }

        for (int i = k; i < n; i++) {
            int c = nums[i];
            if (c > queue.peek()) {
                queue.add(c);
                queue.poll();
            }
        }


        return queue.peek();
    }
}
