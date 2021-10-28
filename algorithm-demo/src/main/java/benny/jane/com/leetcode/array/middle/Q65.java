package benny.jane.com.leetcode.array.middle;


/**
 * 162. 寻找峰值
 * https://leetcode-cn.com/problems/find-peak-element/
 */
public class Q65 {
    // 该题目中各个数值不等

    // 一般思路
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len > 1) {
            if (nums[0] > nums[1]) {
                return 0;
            }
            if (nums[len - 1] > nums[len - 2]) {
                return len - 1;
            }
        }
        int start = 1;
        while (start < len - 1) {
            if (nums[start] > nums[start - 1]
                    && nums[start] > nums[start + 1]) {
                return start;
            } else {
                start++;
            }
        }
        return -1;
    }

    // 分情况讨论，只需要判断 nums[i] > nums[i +1]
    public int findPeakElement2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }

    // 递归二分法
    public int findPeakElement3(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    public int search(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int mid = (l + r) / 2;
        // 判断mid点处于局部上升 or 局部下降序列中
        if (nums[mid] > nums[mid + 1])
            return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }

    // 迭代二分查找
    public int findPeakElement4(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}


