package org.example.com.base.tree.segment;

import java.util.HashMap;
import java.util.Map;

public class SubarraysWithMoreZerosThanOnes {
    // FIXME 超时
    public int subarraysWithMoreZerosThanOnes1(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int cnt = nums[i] == 1 ? 1 : 0;
            pre[i + 1] = pre[i] + cnt;
        }

        int ans = 0;
        int mod = (int) 1e9 + 7;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                int total = j - i + 1;
                int oneCnt = pre[j] - pre[i - 1];
                if (oneCnt > total - oneCnt) {
                    ans += 1;
                    ans %= mod;
                }
            }
        }

        return ans;
    }

    // O(n)方案
    public int subarraysWithMoreZerosThanOnes(int[] nums) {
        /**
         * 假设当前位置累计 1的数量 - 0的数量差值 为 x，x大于0
         * 则 当前位置满足条件子数组 = 差值为x-1的子数组数量 + 差值为x-2的子数组数量... + 差值为0的子数组数量
         */
        // 每个位置的子数组数量 =

        int ans = 0;
        int mod = (int) 1e9 + 7;

        int n = nums.length;
        // map: 记录已遍历数组区内，（1的数量 - 0的数量）的差值 对应的子数组数量
        // 记录不同插值的子数组数量（相同插值的情况会合并）
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        // sum： 记录 1的数量 - 0的数量 的差值
        // cnt 记录满足差值 < sum的子数组数量
        int cnt = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                // 先加上差值为sum的子数组数量
                cnt += map.get(sum);
                sum++;
            } else {
                sum--;
                // 需要减去 差值 等于 当前位置差值的数量
                // cnt：只记录差值 [0, sum)范围的子数组数量
                cnt -= map.getOrDefault(sum, 0);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
            ans += cnt;
            ans %= mod;
        }

        return ans;
    }

    public static void main(String[] args) {
        SubarraysWithMoreZerosThanOnes q = new SubarraysWithMoreZerosThanOnes();

        int[] array = {
                0, 1, 1, 0, 1
        };
        q.subarraysWithMoreZerosThanOnes(array);
    }
}
