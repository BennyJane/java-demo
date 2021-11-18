package org.exmaple.com.leetcode.dp.simple;

/**
 * 553. 最优除法
 * https://leetcode-cn.com/problems/optimal-division/
 */
public class Q8 {
    // 数学证明
    public String optimalDivision(int[] nums) {
        if (nums.length == 1)
            return nums[0] + "";
        if (nums.length == 2)
            return nums[0] + "/" + nums[1];
        StringBuilder res = new StringBuilder(nums[0] + "/(" + nums[1]);
        for (int i = 2; i < nums.length; i++) {
            res.append("/" + nums[i]);
        }
        res.append(")");
        return res.toString();
    }


    public static void main(String[] args) {
        Q8 q = new Q8();
        int[] nums = new int[]{
                1000, 100, 10, 2
        };
        q.optimalDivision(nums);
    }
}


