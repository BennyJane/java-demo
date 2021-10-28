package benny.jane.com.leetcode.array.middle;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 581. 最短无序连续子数组
 * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
 */
public class Q61 {
    // 需要考虑重复值
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int left = 0;
        int min = nums[len - 1];
        int right = len - 1;
        boolean flag = true;
        for (int i = len - 1; i > 0; i--) {
            int cur = nums[i];
            int next = nums[i - 1];
            if (cur < next) {
                if (flag) {
                    right = i;
                    while (right + 1 < len && nums[right + 1] == nums[right]) {
                        right++;
                    }
                    flag = false;
                }
                min = Math.min(min, cur);
            }

            if (min >= next && min < cur) {
                left = i;
            }
        }

        return flag ? 0 : right - left + 1;
    }

    public static void main(String[] args) {
        Q61 q = new Q61();
//        int[] nums = new int[]{2, 6, 4, 8, 10, 9, 15};
        int[] nums = new int[]{1,2,3,4};
//        int[] nums = new int[]{1, 3, 2, 2, 2};
//        int[] nums = new int[]{1, 2, 3, 5, 4};
//        int[] nums = new int[]{2, 1};
        q.findUnsortedSubarray(nums);
    }
}


