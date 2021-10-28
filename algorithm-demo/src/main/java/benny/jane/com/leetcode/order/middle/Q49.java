package benny.jane.com.leetcode.order.middle;


/**
 * 45. 跳跃游戏 II
 * https://leetcode-cn.com/problems/jump-game-ii/
 * <p>
 * 求最小跳跃次数；注意题目相比于版本1.0，明显缩小了数组长度
 */
public class Q49 {

    private int ans = Integer.MAX_VALUE;

    public int jump(int[] nums) {
        int len = nums.length;
        dfs(nums, 0, 0, 0);
        return ans;
    }

    private void dfs(int[] nums, int target, int index, int count) {
        if (target >= nums.length - 1) {
            if (count < ans) {
                ans = count;
            }
            return;
        }

        if (index >= nums.length) {
            return;
        }

        int n = nums[index];

        for (int i = 1; i <= n; i++) {
            if (nums[index + 1] == 0 && index + 1 < nums.length - 1) {
                continue;
            }
            count += 1;
            dfs(nums, target + i, index + 1, count);
            count -= 1;
        }
    }

    public static void main(String[] args) {
        Q49 q = new Q49();
//        int[] nums = {2, 0, 0};
//        int[] nums = {0, 0};
        int[] nums = {2, 0, 2, 0, 1};
//        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(q.jump(nums));
    }
}


