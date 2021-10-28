package benny.jane.com.leetcode.array.middle;

import java.util.HashMap;
import java.util.Map;

/**
 * 137. 只出现一次的数字 II
 * https://leetcode-cn.com/problems/single-number-ii/
 */
public class Q82 {
    // 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
    // 位运算： TODO 难
    public int singleNumber(int[] nums) {
        return 0;
    }

    // 哈希表： 时间复杂度O(n) 空间复杂度O(n)
    public int singleNumber2(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int num = entry.getKey(), occ = entry.getValue();
            if (occ == 1) {
                ans = num;
                break;
            }
        }
        return ans;
    }
}


