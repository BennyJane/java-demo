package org.exmaple.com.leetcode.order.middle;

/**
 * 1780. 判断一个数字是否可以表示成三的幂的和
 * https://leetcode-cn.com/problems/check-if-number-is-a-sum-of-powers-of-three/
 *
 * 参考答案： https://leetcode-cn.com/problems/check-if-number-is-a-sum-of-powers-of-three/solution/zhuan-hua-wei-san-jin-zhi-shu-by-chengzi-fz3j/
 *
 */
public class Q38 {
    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 == 2) return false;
            n = n / 3;
        }
        return true;
    }
}

