package org.exmaple.com.leetcode.array.middle;

import java.util.HashSet;
import java.util.Set;

/**
 * 523. 连续的子数组和
 * https://leetcode-cn.com/problems/continuous-subarray-sum/
 */
public class Q53 {
    // 使用前缀和方法
    // TODO 超时
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] preSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            // 错误相加
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int length = preSum.length;

        // preSum数组的总长度已经变化了，不是len
        for (int i = 2; i < length; i++) {
            for (int j = 0; j <= i - 2; j++) {
                int diff = preSum[i] - preSum[j];
                if (diff % k == 0 && i - j >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    // https://leetcode-cn.com/problems/continuous-subarray-sum/solution/gong-shui-san-xie-tuo-zhan-wei-qiu-fang-1juse/
    // 要使得两者除 kk 相减为整数，需要满足 sum[j]sum[j] 和 sum[i - 1]sum[i−1] 对 kk 取余相同。
    public boolean checkSubarraySum2(int[] nums, int k) {
        int len = nums.length;
        int[] preSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            // 错误相加
            preSum[i + 1] = preSum[i] + nums[i];
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 2; i < preSum.length; i++) {
            int mod = preSum[i - 2] % k;    // 确保只计算出满足长度为2的子数组和的余数
            if (set.contains(preSum[i] % k)) {
                return true;
            }
            set.add(mod);
        }
        return false;
    }


    public static void main(String[] args) {
        Q53 q = new Q53();
        int[] nums = new int[]{23, 2, 4, 6, 6};
        System.out.println(q.checkSubarraySum(nums, 7));
    }
}


