package org.example.com.leetcode.dp.simple;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 473. 火柴拼正方形
 * https://leetcode-cn.com/problems/matchsticks-to-square/
 */
public class Q7_2 {
    // 深度优先

    public List<Integer> nums;
    public int[] sums;
    public int possibleSquareSide;

    public boolean makesquare(int[] matchsticks) {
        sums = new int[4];
        if (matchsticks == null || matchsticks.length == 0) {
            return false;
        }
        int perimeter = 0;  // 周长
        for (int n : matchsticks) {
            perimeter += n;
        }
        possibleSquareSide = perimeter / 4;
        if (perimeter % 4 != 0) {
            return false;
        }

        // 降序排列：剪枝，优化效率
        this.nums = Arrays.stream(matchsticks)
                .boxed()    // 装箱为Integer
                .collect(Collectors.toList());
        Collections.sort(this.nums, Collections.reverseOrder());
        return dfs(0);
    }

    // 讨论每个值在不同分组的情况
    private boolean dfs(int index) {
        // 判断临界点时，是否已经划分为4等分
        if (index == nums.size()) {
            return sums[0] == sums[1] && sums[1] == sums[2] && sums[2] == sums[3];
        }

        int element = nums.get(index);

        // 考虑将该数值放到4个分组的情况
        for (int i = 0; i < 4; i++) {
            if (sums[i] + element <= possibleSquareSide) {
                sums[i] += element;
                if (dfs(index + 1)) {
                    return true;
                }
                // 回溯法
                sums[i] -= element;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Q7_2 q = new Q7_2();
        int[] nums = new int[]{
//                1, 1, 2, 2, 2
                5, 5, 5, 5, 16, 4, 4, 4, 4, 4, 3, 3, 3, 3, 4
        };
        q.makesquare(nums);
    }
}


