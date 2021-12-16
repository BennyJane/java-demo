package org.example.com.leetcode.order.middle;

/**
 * 154. 寻找旋转排序数组中的最小值 II
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 */
public class Q46 {

    public int ans = Integer.MAX_VALUE; //最小值
    private int start;
    private int end;

    public int findMin(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return nums[0];
        }

        start = 0;
        end = len - 1;
        while (start + 1 < nums.length && nums[start] == nums[start + 1]) {
            start++;
        }

        while (end - 1 >= start && nums[end] == nums[end - 1]) {
            end--;
        }
        dfs(nums, start, end);
        return ans;
    }

    public void dfs(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }

        while (left <= right) {
            int mid = (left + right) / 2;
            int val = nums[mid];
            if (ans > val) {
                ans = val;
            }
            if (nums[0] <= val) {
                if (ans > nums[0]) {
                    ans = nums[0];
                }
                left = mid + 1;
            } else {
                if (ans > nums[right]) {
                    ans = nums[right];
                }
                right = mid - 1;
            }
        }
    }

    public static void main(String[] args) {
        Q46 q = new Q46();
        int[] nums1 = {10,1,10,10,10};
        System.out.println(q.findMin(nums1));
    }
}


