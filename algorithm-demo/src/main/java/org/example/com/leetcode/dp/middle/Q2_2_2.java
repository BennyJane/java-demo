package org.example.com.leetcode.dp.middle;

/**
 * 剑指 Offer 51. 数组中的逆序对
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 */
public class Q2_2_2 {
    // 归并排序
    public int reversePairs(int[] nums) {
        int len = nums.length;

        if (len < 2) {
            return 0;
        }

        // 需要确定是否可以修改原始数组
        int[] copy = new int[len];
        for (int i = 0; i < len; i++) {
            copy[i] = nums[i];
        }

        // 临时数组：作为合并后的区间
        int[] temp = new int[len];
        return reversePairs(copy, 0, len - 1, temp);
    }

    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }

        // 警惕(尤其是二分法)： (right - left) /2 的bug
        int mid = left + (right - left) / 2;
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);

        // 两个区间直接合并
        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }

        int crossPairs = mergeAndCount(nums, left, mid, right, temp);
        return leftPairs + rightPairs + crossPairs;
    }

    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        // 复制原数组内容，保留原数据；原数组nums修改后作为最终结果
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;

        int count = 0;
        for (int k = left; k <= right; k++) {   // 遍历待合并区间的总长度
            if (i == mid + 1) {        // 左区间遍历末尾，将剩余右区间添加到合并后的区间
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {    // 右区间遍历结束，处理左区间
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {    // 此处必须包含该等号
                nums[k] = temp[i];
                i++;
            } else { // 大于情况
                nums[k] = temp[j];
                j++;
                //  计算左侧区间([left, mid] 闭区间)剩余数量
                count += (mid - i + 1);
            }
        }
        return count;
    }


    public static void main(String[] args) {
        Q2_2_2 q = new Q2_2_2();
        int[] nums = new int[]{
                7, 5, 6, 4
        };
        q.reversePairs(nums);
    }

}


