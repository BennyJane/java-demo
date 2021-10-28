package benny.jane.com.leetcode.array.simple;

/**
 * 66. 加一
 * https://leetcode-cn.com/problems/plus-one/
 */
public class Q28 {
    // 考虑进位： 9 +1 = 10
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] += 1;
                return digits;
            }
            digits[i] = 0;
        }
        int[] ans = new int[len +1];
        ans[0] =1;
        return ans;
    }
}


