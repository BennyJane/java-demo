package org.example.com.leetcode.order.middle;

/**
 * 55. 跳跃游戏
 * https://leetcode-cn.com/problems/jump-game/
 */
public class Q48_2 {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return true;
        }
        return dfs(nums, len - 1);
    }

    // TODO 还是部分测试用例超时
    private boolean dfs(int[] nums, int tail) {
        if (tail == 0) {
            return true;
        }

        for (int i = tail - 1; i >= 0; i--) {
            int n = nums[i];
            if (i + n >= tail) {
                if (dfs(nums, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q48_2 q = new Q48_2();
//        int[] nums = {2, 0, 0};
//        int[] nums = {0, 0};
//        int[] nums = {2, 3, 1, 1, 4};
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(q.canJump(nums));
    }
}


