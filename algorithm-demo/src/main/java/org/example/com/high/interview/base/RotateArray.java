package org.example.com.high.interview.base;

import java.util.ArrayList;
import java.util.List;

public class RotateArray {
}


/**
 * 33. 搜索旋转排序数组
 * 数组内值互不相等
 */
class Solution0 {
    public int search(int[] nums, int target) {
        int n = nums.length;
        // 旋转后特征：
        // 左侧区间[0, X]为递增区间，且 >= nums[0]
        // 左侧区间[X + 1, n-1]为递增区间，且 < nums[0]
        int pivot = nums[0];

        int left = 0, right = n - 1;
        while (left <= right) { // 包含等号
            int mid = left + (right - left) / 2;
            int val = nums[mid];
            // 快速退出： val = target
            if (val == target) {
                return mid;
            }
            // 判断mid值位于左右哪个区间
            // FIXME target ！= val, 即mid位置不满足要求，可以舍弃
            if (val >= pivot) {
                // TODO 左区间左侧[0, mid)为递增，不包含拐点
                if (target >= pivot && target < val) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // TODO 左区间右侧(mid, n-1]为递增，不包含拐点
                // nums[mid] < target <= nums[n -1]
                // nums[mid] < target < nums[0]
                if (target > val && target < pivot) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}


/**
 * 10.03 搜索旋转数组
 * 数组内存在相等值(重复值)
 * 滚动数组
 */
class Solution1 {
    public int search2(int[] arr, int target) {
        List<Integer> list = new ArrayList();
        for (int c : arr) {
            list.add(c);
        }
        return list.indexOf(target);
    }

    /**
     * 旋转：将整体有序的数组分成 左右两端有序部分
     * 核心：确保左侧区间最小值 > 右侧区间最大值，即 arr[0] > arr[n-1]
     * 二分计算中间值后，通过判断 midVal 与 arr[0] 的关系，确定mid位于左侧区间、还是右侧区间
     */
    public int search(int[] arr, int target) {
        int n = arr.length;
        int left = 0, right = n - 1;

        // 处理特殊情况：目标值位于首尾，直接返回索引0
        int pivot = arr[0];
        if (target == pivot) return 0;

        // TODO 处理右侧区间，使arr[right] < pivot 成立
        while (arr[right] == pivot) {
            right--;
        }

        // FIXME 处理左侧的时，反而会因为遍历太多元素，造成耗时
        while (arr[left] == pivot) {
            left++;
        }

        int ans = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int val = arr[mid];
            if (val == target) {
                // 可能存在多个目标值，需要记录最小索引位置
                ans = Math.min(ans, mid);
                // TODO 当val = target时，下一个满足条件的值，一定在左侧
                right = mid - 1;
                continue;
            }
            // 根据 val 与 arr[0] 的比较，确定 mid 位于左右哪个区间
            // FIXME 等于pivot的情况，只存在左区间！！！
            if (val >= pivot) {
                // 单个区间上，再划分出局部有序区间[0, mid) (mid + 1 , r]两种情况
                if (target > pivot && target < val) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > val && target < pivot) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    public int search3(int[] arr, int target) {
        int n = arr.length;
        if (n == 0) return -1;

        int pivot = arr[0];
        if (pivot == target) return 0;

        int left = 0, right = n - 1;
        while (arr[right] == pivot) right--;

        // 先找到分界点: 左区间的最后一个位置索引
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            // TODO 合并处理后，需要修改mid计算方式，使得mid计算结果偏右
            if (arr[mid] > pivot) {
                // 左侧区间范围[mid, X]
                left = mid;
            } else if (arr[mid] == pivot) {
                // 左侧区间范围[mid, X]
                left = mid;
            } else {    // 位于右区间，一定不符合条件
                right = mid - 1;
            }
        }
        // 此时：left = right, 且位于左区间的最后一个位置

        // target 位于左区间[0, 临时索引]
        if (target > pivot) {
            left = 0;
        }
        // target 位于右区间[临界索引 + 1, n -1]
        if (target < pivot) {
            left++;
            right = n - 1;
        }

        // 再次使用二分查找
        while (left < right) {
            // right = left + 1 时，mid偏左
            int mid = left + (right - left) / 2;
            int val = arr[mid];
            if (val > target) {
                right = mid - 1;
            } else if (val == target) {
                // 结果一定再mid左侧，且包含mid索引
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return arr[right] == target ? right : -1;
    }

    public int search4(int[] arr, int target) {
        int n = arr.length;
        if (n == 0) return -1;

        int pivot = arr[0];
        if (pivot == target) return 0;

        int left = 0, right = n - 1;
        while (arr[right] == pivot) right--;

        // 先找到分界点: 左区间的最后一个位置索引
        while (left < right) {  // 相等即退出
            // FIXME: 处理left,right相邻情况，mid计算结果偏右侧 ==》 left = mid，两者需要匹配
            int mid = left + (right - left + 1) / 2;
            if (arr[mid] >= pivot) {
                left = mid;
            } else {    // 位于右区间，一定不符合条件
                right = mid - 1;
            }
        }
        // 此时：left = right, 且位于左区间的最后一个位置

        // target 位于左区间[0, 临时索引]
        if (target > pivot) {
            left = 0;
        }
        // target 位于右区间[临界索引 + 1, n -1]
        if (target < pivot) {
            left++;
            right = n - 1;
        }

        // 再次使用二分查找
        while (left < right) {
            // FIXME 处理 right = left + 1 时，mid偏左 ==》 right = mid
            int mid = left + (right - left) / 2;
            int val = arr[mid];
            if (val >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return arr[right] == target ? right : -1;
    }

}