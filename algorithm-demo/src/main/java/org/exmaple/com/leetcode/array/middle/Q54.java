package org.exmaple.com.leetcode.array.middle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 565. 数组嵌套
 * https://leetcode-cn.com/problems/array-nesting/
 */
public class Q54 {

    // 耗时过长
    private int ans = 0;
    HashMap<Integer, Set<Integer>> map;

    public int arrayNesting(int[] nums) {
        // 第一个int： 数组的索引
        // 第二个int: 索引对应的S最大值
        map = new HashMap<>();
        Set<Integer> set;
        int len;
        for (int i = 0; i < nums.length; i++) {
            set = new HashSet<>();
            dfs(nums, i, set);
            map.put(i, set);
        }

        return ans;
    }

    private void dfs(int[] nums, int index, Set<Integer> set) {
        int nextIndex = nums[index];
        int len = set.size();
        if (set.contains(nextIndex)) {
            if (ans < len) {
                ans = len;
            }
            return;
        }

        if (map.containsKey(nextIndex)) {
            Set<Integer> oldSet = map.get(nextIndex);
            for (int n : oldSet) {
                if (!set.contains(n)) {
                    set.add(n);
                    len++;
                }
            }
            if (ans < len) {
                ans = len;
            }
            return;
        }

        set.add(nextIndex);
        dfs(nums, nextIndex, set);
    }

    public static void main(String[] args) {
        Q54 q = new Q54();
        int[] nums = new int[]{5, 4, 0, 3, 1, 6, 2};
        System.out.println(q.arrayNesting(nums));
    }
}


