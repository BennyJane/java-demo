package benny.jane.com.leetcode.array.middle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 565. 数组嵌套
 * https://leetcode-cn.com/problems/array-nesting/
 */
public class Q54_2 {

    // TODO 核心：终止条件是 终止索引==起始索引 ？？没有证明

    // 暴力求解：超时
    public int arrayNesting(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];
            int count = 0;
            do {
                start = nums[start];
                count++;
            }
            while (start != nums[i]);
            ans = Math.max(ans, count);
        }
        return ans;
    }

    // 优化执行效率
    // 1. 每次构成的循环中涉及的数字，不会参与到其他循环中；即各个循环时相互隔离的
    // 2. 同一个循环中数字对应的长度相同
    public int arrayNesting2(int[] nums) {
        int len = nums.length;
        boolean[] visited = new boolean[len];
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                int start = nums[i];
                int count = 0;
                do {
                    start = nums[start];
                    count++;
                    visited[i] = true;
                }
                while (start != nums[i]);
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    // 不适用额外空间
    public int arrayNesting3(int[] nums) {
        int len = nums.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != -1) {
                int start = nums[i];
                int count = 0;
                while (nums[start] != -1) {
                    int temp = start;
                    start = nums[start];
                    count++;
                    nums[temp] = -1;
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Q54_2 q = new Q54_2();
        int[] nums = new int[]{5, 4, 0, 3, 1, 6, 2};
        System.out.println(q.arrayNesting(nums));
    }
}


