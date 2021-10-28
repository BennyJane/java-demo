package benny.jane.com.leetcode.order.middle;

/**
 * 55. 跳跃游戏
 * https://leetcode-cn.com/problems/jump-game/
 */
public class Q48 {
    // TODO 部分示例超时

    public boolean canJump(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return true;
        }
        return dfs(nums, len - 1, 0);
    }

    private boolean dfs(int[] nums, int sum, int index) {
        if (sum == 0) {
            return true;
        }

        if (index >= nums.length) {
            return false;
        }

        int n = nums[index];

        for (int i = n; i >= 1; i--) {
            if (dfs(nums, sum - i, index + i)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q48 q = new Q48();
//        int[] nums = {2, 0, 0};
//        int[] nums = {0, 0};
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(q.canJump(nums));
    }
}


