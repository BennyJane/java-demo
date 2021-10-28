package benny.jane.com.leetcode.array.middle;


import java.util.ArrayList;
import java.util.List;

/**
 * 287. 寻找重复数
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 */
public class Q71_2 {
    public int findDuplicates(int[] nums) {
        int len = nums.length;
        int ans = 0;

        for (int i = 0; i < len; i++) {
            int cur = Math.abs(nums[i]);    // 原始数值的绝对值
            if (nums[cur - 1] < 0) {
                ans = i;
                break;
            } else {
                // 将原始数值作为索引映射的数值，修改为其相反数
                nums[cur - 1] = -1 * nums[cur - 1];
            }
        }

        return ans;
    }
}


