package org.example.com.offer.special;

import com.sun.xml.internal.org.jvnet.mimepull.CleanUpExecutorFactory;

import java.lang.reflect.Array;
import java.util.*;

public class longestConsecutive {
    // 暴力求解
    public int longestConsecutive1(int[] nums) {
        Arrays.sort(nums);

        int ans = 0;
        int width = 1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                // FIXME 相同值直接跳过
                if (nums[i] == nums[i - 1]) {
                    continue;
                } else if (nums[i] == nums[i - 1] + 1) {
                    width += 1;
                } else {
                    width = 1;
                }
            }
            ans = Math.max(ans, width);
        }

        return ans;
    }

    public int longestConsecutive2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        Arrays.sort(nums);

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                if (nums[i] == nums[i - 1]) {
                    dp[i] = dp[i - 1];
                } else if (nums[i] == nums[i - 1] + 1) {
                    dp[i] += dp[i - 1];
                } else {
                    dp[i] = 1;
                }
            } else {
                dp[i] = 1;
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }

    // 实现O(n)的复杂度: 基于哈希表
    public int longestConsecutive(int[] nums) {
        // 快速判断目标值是否存在
        // 去重
        Set<Integer> numSet = new HashSet<>();
        for (int n : nums) {
            numSet.add(n);
        }

        int maxWidth = 0;
        for (int num : nums) {
            // 只需要遍历前一个值不在原数组中的值，即每段连续数值的起点
            // TODO 核心点：每段连续子集的起点 的前一个值一定不在数组内
            if (!numSet.contains(num - 1)) {
                int cur = num;
                int width = 1;

                // 判断连续序列的长度
                while (numSet.contains(cur + 1)) {
                    cur += 1;
                    width++;
                }

                maxWidth = Math.max(maxWidth, width);
            }
        }
        return maxWidth;
    }

    // 基于哈希表
    public int longestConsecutive3(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxWidth = 0;
        for (Integer cur : nums) {
            if (!map.containsKey(cur)) {
                // 获取左右相邻数值的连续
                int left = map.getOrDefault(cur - 1, 0);
                int right = map.getOrDefault(cur + 1, 0);

                int curWidth = 1 + left + right;
                maxWidth = Math.max(maxWidth, curWidth);

                map.put(cur, curWidth);
                // TODO 更新相邻位置长度： 保留当前cur的长度
                map.put(cur - left, curWidth);
                map.put(cur + right, curWidth);
            }
        }

        return maxWidth;
    }
}
