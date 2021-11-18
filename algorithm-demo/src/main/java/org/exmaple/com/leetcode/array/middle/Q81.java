package org.exmaple.com.leetcode.array.middle;

/**
 * 134. 加油站
 * https://leetcode-cn.com/problems/gas-station/
 */
public class Q81 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int left = 0;
        while (left < len) {
            // 寻找合适的起点
            while (left < len && gas[left] < cost[left]) {
                left++;
            }
            if (left >= len) {
                break;
            }
            int sum = 0;
            int right = left;
            while (right < len + left
                    && sum + gas[right % len] >= cost[right % len]) {
                sum = (sum + gas[right % len]) - cost[right % len];
                right++;
            }
            if (right >= len + left) {
                return left;
            }
            left++;
        }

        return -1;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int len = gas.length;

        for (int i = 0; i < len; i++) {
            if (gas[i] < cost[i]) {
                continue;
            }
            int sum = 0;
            int right = i;
            while (right < len + i
                    && sum + gas[right % len] >= cost[right % len]) {
                sum = (sum + gas[right % len]) - cost[right % len];
                right++;
            }
            if (right >= len + i) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Q81 q = new Q81();

        int[] nums1 = new int[]{1, 2, 3, 4, 5};
        int[] nums2 = new int[]{3, 4, 5, 1, 2};
        q.canCompleteCircuit(nums1, nums2);
    }
}


