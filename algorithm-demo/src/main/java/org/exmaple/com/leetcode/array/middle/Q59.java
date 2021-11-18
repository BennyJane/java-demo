package org.exmaple.com.leetcode.array.middle;

import java.util.*;

/**
 * 90. 子集 II
 * https://leetcode-cn.com/problems/subsets-ii/
 */
public class Q59 {

    private Set<List<Integer>> res;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);

        res = new HashSet<>();
        dfs(nums, 0, list);
        return new ArrayList<>(res);
    }

    private void dfs(int[] nums, int i, List<Integer> arr) {
        if (i >= nums.length) {
            res.add(new ArrayList<>(arr));
        } else {
            dfs(nums, i + 1, arr);
            int n = nums[i];
            arr.add(n);
            dfs(nums, i + 1, arr);
            arr.remove(arr.size() - 1);
        }
    }

    public static void main(String[] args) {
        Q59 q = new Q59();
        int[] nums = new int[]{1, 2, 2};
        q.subsetsWithDup(nums);
    }
}


