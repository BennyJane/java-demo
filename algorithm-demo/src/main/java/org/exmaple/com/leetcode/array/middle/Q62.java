package org.exmaple.com.leetcode.array.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class Q62 {

    boolean[] used;
    int ans = 0;

    // TODO 超时
    public int triangleNumber(int[] nums) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
        List<Integer> sum = new ArrayList<>();
        dfs(nums, 0, sum);
        return ans;
    }

    private void dfs(int[] nums, int k, List<Integer> arr) {
        if (arr.size() >= 3) {
            int sum = arr.get(0) + arr.get(1);
            if (sum > arr.get(2)) {   // 满足构成三角形
                ans++;
            }
            return;
        }

        if (k >= nums.length) {
            return;
        }

        for (int i = k; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            int cur = nums[i];
            if (arr.size() == 2) {
                int sum = arr.get(0) + arr.get(1);
                if (sum <= cur) {   // 不满足构成三角形
                    break;  // 后续的值都不能满足条件
                }
            }
            // TODO 这里不需要添加该措施
//            dfs(nums, i + 1, arr);  // 不使用该值
            used[i] = true;
            arr.add(nums[i]);
            dfs(nums, i + 1, arr);
            arr.remove(arr.size() - 1);
            used[i] = false;
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 3, 4};

        Q62 q = new Q62();
        q.triangleNumber(nums);
    }
}


