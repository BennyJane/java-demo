package com.benny.learning.algorithm.leetcode.dp.simple;

import javafx.util.Pair;

import java.util.HashMap;

/**
 * 473. 火柴拼正方形
 * https://leetcode-cn.com/problems/matchsticks-to-square/
 */
public class Q7_3 {
    // 动态规划 + 状态压缩
    // 使用二进制
    // The memoization cache to be used during recursion.
    // 记忆化存储，在递归中使用
    public HashMap<Pair<Integer, Integer>, Boolean> memo;

    // Array containing our matchsticks.
    public int[] nums;

    // Possible side of our square depending on the total sum of all the matchsticks. 
    public int possibleSquareSide;

    // Default constructor to initialise our memo cache.
    public Q7_3() {
        this.memo = new HashMap<Pair<Integer, Integer>, Boolean>();
    }

    // Main DP function.
    public boolean recurse(Integer mask, Integer sidesDone) {
        int total = 0;
        int L = this.nums.length;

        // The memo key for this recursion
        Pair<Integer, Integer> memoKey = new Pair(mask, sidesDone);

        // Find out the sum of matchsticks used till now.
        for (int i = L - 1; i >= 0; i--) {
            if ((mask & (1 << i)) == 0) {
                total += this.nums[L - 1 - i];
            }
        }

        // If the sum if divisible by our square's side, then we increment our number of complete sides formed variable.
        if (total > 0 && total % this.possibleSquareSide == 0) {
            sidesDone++;
        }

        // Base case.
        if (sidesDone == 3) {
            return true;
        }


        // Return precomputed results
        if (this.memo.containsKey(memoKey)) {
            return this.memo.get(memoKey);
        }

        boolean ans = false;
        int c = total / this.possibleSquareSide;

        // Remaining vlength in the current partially formed side.
        int rem = this.possibleSquareSide * (c + 1) - total;

        // Try out all remaining options (that are valid)
        for (int i = L - 1; i >= 0; i--) {
            if (this.nums[L - 1 - i] <= rem && (mask & (1 << i)) > 0) {
                if (this.recurse(mask ^ (1 << i), sidesDone)) {
                    ans = true;
                    break;
                }
            }
        }

        // Cache the computed results.
        this.memo.put(memoKey, ans);
        return ans;
    }

    public boolean makesquare(int[] nums) {

        // Empty matchsticks.
        if (nums == null || nums.length == 0) {
            return false;
        }

        // Find the perimeter of the square (if at all possible)
        int L = nums.length;
        int perimeter = 0;
        for (int i = 0; i < L; i++) {
            perimeter += nums[i];
        }

        int possibleSquareSide = perimeter / 4;
        if (possibleSquareSide * 4 != perimeter) {
            return false;
        }

        this.nums = nums;
        this.possibleSquareSide = possibleSquareSide;
        return this.recurse((1 << L) - 1, 0);
    }


    public static void main(String[] args) {
        Q7_3 q = new Q7_3();
        int[] nums = new int[]{
//                1, 1, 2, 2, 2
                5, 5, 5, 5, 16, 4, 4, 4, 4, 4, 3, 3, 3, 3, 4
        };
        q.makesquare(nums);
    }
}


