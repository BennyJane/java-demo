package benny.jane.com.leetcode.order.middle;

/**
 * 81. 搜索旋转排序数组 II
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
 * <p>
 * 进阶：
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 */
public class Q44 {

    private int start;
    private int end;

    public boolean search(int[] nums, int target) {
        int len = nums.length;
        if (len < 2) {
            return nums[0] == target ? true : false;
        }

        start = 0;
        end = len - 1;
        while (start + 1 < nums.length && nums[start] == nums[start + 1]) {
            start++;
        }

        while (end - 1 >= start && nums[end] == nums[end - 1]) {
            end--;
        }

        return midSearch(nums, start, end, target);
    }

    public boolean midSearch(int[] nums, int left, int right, int target) {
        if (left > right) {
            return false;
        }
        while (left <= right) {
            int mid = (left + right) / 2;
            int val = nums[mid];
            if (val == target) {
                return true;
            }

            if (nums[start] <= val) {   // mid 左侧为递增序列
                if (target >= nums[left] && target < val) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {   // mid 右侧为递增序列
                if (target <= nums[right] && target > val) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q44 q = new Q44();
//        int[] nums = {2,5,6,0,0,1,2};
//        System.out.println(q.search(nums, 0));
//
        int[] nums1 = {1, 1};
        System.out.println(q.search(nums1, 2));
//        int[] nums2 = {1, 0, 1, 1, 1};
//        System.out.println(q.search(nums2, 0));
    }
}


