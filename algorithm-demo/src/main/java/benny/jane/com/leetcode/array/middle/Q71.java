package benny.jane.com.leetcode.array.middle;


import java.util.ArrayList;
import java.util.List;

/**
 * 442. 数组中重复的数据
 * https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/
 */
public class Q71 {
    public List<Integer> findDuplicates(int[] nums) {
        int len = nums.length;

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            int cur = Math.abs(nums[i]);    // 原始数值的绝对值
            if (nums[cur - 1] < 0) {
                ans.add(cur);
            } else {
                // 将原始数值作为索引映射的数值，修改为其相反数
                nums[cur - 1] = -1 * nums[cur - 1];
            }
        }

        return ans;
    }
}


