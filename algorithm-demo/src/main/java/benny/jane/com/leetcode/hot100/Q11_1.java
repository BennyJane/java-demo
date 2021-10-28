package benny.jane.com.leetcode.hot100;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 11. 盛最多水的容器
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class Q11_1 {
    // 贪心思想： 从暴力求解推导
    public int maxArea(int[] height) {
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int ans = 0;
        while (left < right) {
            int area = 0;
            if (height[left] <= height[right]) {
                area = height[left] * (right - left);
                left++;
            } else {
                area = height[right] * (right - left);
                right--;
            }
            ans = Math.max(ans, area);
        }

        return ans;
    }

    public static void main(String[] args) {
        Q11_1 q = new Q11_1();
        int[] nums = new int[]{
                4, 3, 2, 1, 4
//                1, 1
//                1, 8, 6, 2, 5, 4, 8, 3, 7

        };
        q.maxArea(nums);
    }
}


