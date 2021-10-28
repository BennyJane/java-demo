package benny.jane.com.leetcode.dp.middle;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 315. 计算右侧小于当前元素的个数
 * https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/
 */
public class Q2_1 {


    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int len = nums.length;
        if (len < 2) {
            list.add(0);
            return list;
        }

        //dp[i]: 左侧小于当前值的数量
        int[] dp = new int[len];
        boolean[] larger = new boolean[len];
        larger[len - 1] = true;
        boolean[] less = new boolean[len];
        for (int i = len - 2; i >= 0; i--) {
            int cur = nums[i];
            int change = 0; // 变动的数量
            if (cur == nums[i + 1]) {
                dp[i] = dp[i + 1] + change;
            } else if (cur > nums[i + 1]) {
                for (int index = i; index < len; index++) {
                    if (larger[index] && nums[index] < cur) {
                        change++;
                        larger[index] = false;
                        less[index] = true;
                    }
                }
                dp[i] = dp[i + 1] + change;
            } else {    // 小于
                for (int index = i; index < len; index++) {
                    if (less[index] && nums[index] < cur) {
                        change++;
                    } else {
                        larger[index] = true;
                        less[index] = false;
                    }
                }
                dp[i] = change;
            }
            // 将当前值放到larger的分组内
            larger[i] = true;
        }

        for (int n : dp) {
            list.add(n);
        }
        return list;
    }

    // 暴力求解：逐个索引遍历右侧所有值判断
    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];

        }
        return null;
    }

    public static void main(String[] args) {
        Q2_1 q = new Q2_1();
        int[] people = new int[]{
//                5, 2, 6, 1
//                -1,-1
                2, 1, 0
//                1, 9, 7, 8, 5

        };
        q.countSmaller(people);
    }
}