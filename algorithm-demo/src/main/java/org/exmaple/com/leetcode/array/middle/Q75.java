package org.exmaple.com.leetcode.array.middle;

/**
 * 665. 非递减数列
 * https://leetcode-cn.com/problems/non-decreasing-array/
 */
public class Q75 {
    public boolean checkPossibility(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; ++i) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                nums[i] = y;
                if (isSorted(nums)) {
                    return true;
                }
                nums[i] = x; // 复原
                nums[i + 1] = x;
                return isSorted(nums);
            }
        }
        return true;
    }

    public boolean isSorted(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; ++i) {
            if (nums[i - 1] > nums[i]) {
                return false;
            }
        }
        return true;
    }


    // 优化
    public boolean checkPossibility2(int[] nums) {
        int n = nums.length, cnt = 0;
        for (int i = 0; i < n - 1; ++i) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                cnt++;
                if (cnt > 1) {
                    return false;
                }
                // 移动 x y 其中一个，确保剩下的部分仍然是非降序排列
                if (i > 0 && y < nums[i - 1]) {
                    // 当 i -1  > i + 1 时，需要将i +1 处的值修改为i处的值(取较大值)
                    nums[i + 1] = x;
                }
            }
        }
        return true;
    }
}


