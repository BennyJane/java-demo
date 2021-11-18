package org.exmaple.com.leetcode.order.middle;


/**
 * 45. 跳跃游戏 II
 * https://leetcode-cn.com/problems/jump-game-ii/
 * <p>
 * 求最小跳跃次数；注意题目相比于版本1.0，明显缩小了数组长度
 */
public class Q49_2 {

    public int jump(int[] nums) {
        int len = nums.length;
        // 上次跳跃可达范围右边界(下次的最右起跳点)
        int lastPosition = 0;    // 需要再次跳跃的位置（上一次跳跃的落点）
        int maxPosition = 0;    // 再次跳跃，可以抵达的最远位置
        int steps = 0;
        for (int i = 0; i < len - 1; i++) { // 最后一个索引不需要参与遍历；否则可能会步数多1
            // 获取[i, lastPosition]范围内，可以到达的最远索引位置
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == lastPosition) { // 如果遍历到下一次跳跃的位置，
                // 应该steps+1，
                // 并将lastPosition更新为当前遍历索引中可以跳跃的最大索引位置
                // 设置下次起跳位置的右边界
                lastPosition = maxPosition;
                steps++;
            }

        }
        return steps;
    }


    public static void main(String[] args) {
        Q49_2 q = new Q49_2();
//        int[] nums = {2, 0, 0};
//        int[] nums = {0, 0};
        int[] nums = {2, 0, 2, 0, 1};
//        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(q.jump(nums));
    }
}


