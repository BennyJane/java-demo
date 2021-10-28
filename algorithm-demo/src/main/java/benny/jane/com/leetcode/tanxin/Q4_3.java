package benny.jane.com.leetcode.tanxin;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/koko-eating-bananas/
 * 875. 爱吃香蕉的珂珂
 *
 * 1231. 分享巧克力 (会员题)
 */
public class Q4_3 {
    // 二分法 + 贪心算法
    public int minEatingSpeed(int[] piles, int h) {
        int sum = Arrays.stream(piles).sum();
        int left = Math.max(sum / h, 1);
        int right = Arrays.stream(piles).max().getAsInt();

        while (left < right) {
            int mid = (left + right) / 2;
            int total = check(piles, mid);
            // TODO 临界条件转换 ==》 浪费时间
            if (total <= h) {   //  TODO 最后满足条件的结果一定出现在右侧断点
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    private int check(int[] piles, int k) {
        int total = 0;
        for (int n : piles) {
            if (n % k == 0) {
                total += (n / k);
            } else {
                total += (n / k + 1);
            }
        }
        return total;
    }


    public static void main(String[] args) {
        Q4_3 q = new Q4_3();
        int[] nums = new int[]{
//                3, 6, 7, 11
//                312884470
                1000000000
        };
        q.minEatingSpeed(nums, 2);
    }
}


