package org.exmaple.com.leetcode.array.simple;

/**
 * 605. 种花问题
 * https://leetcode-cn.com/problems/can-place-flowers/
 * <p>
 * 略麻烦: 可以写题解
 */
public class Q32 {

    // 不断遍历，得到连续0的数量，然后根据左右两侧是否种花，来确定可以增加的数量
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int count = 0;
        int left = 0;

        // 标记左右两侧是否已经种花
        boolean leftOne = false;
        boolean rightOne = false;

        while (left < len) {
            if (flowerbed[left] == 1) {
                left++; // 当前位置种花后，下一个位置一定为0
                leftOne = true;
            } else {
                leftOne = false;
            }
            int right = left;
            while (right < len && flowerbed[right] == 0) {
                // 计算连续0的长度
                right++;
            }
            // 判断右侧是否已经种花
            if (right < len && flowerbed[right] == 1) {
                rightOne = true;
            } else {
                rightOne = false;
            }
            if (rightOne && leftOne) {  // 左右两侧均种花
                count += (right - left - 1) / 2;
            } else if (rightOne || leftOne) {   // 单侧种花
                count += (right - left) / 2;
            } else {    // 两侧均没有；全为0的情况
                // 种在奇数位置，才可以种植最多
                count += (right - left + 1) / 2;
            }
            left = right;
        }

        return count >= n;
    }

    // 优化
    public boolean canPlaceFlowers3(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int count = 0;
        int left = 0;

        // 标记左右两侧是否已经种花
        boolean leftOne = false;
        boolean rightOne = false;

        while (left < len) {
            int right = left;
            while (right < len && flowerbed[right] == 0) {
                // 计算连续0的长度
                right++;
            }
            // 判断右侧是否已经种花
            leftOne = left > 0 && flowerbed[left - 1] == 1;
            rightOne = right < len && flowerbed[right] == 1;
            if (rightOne && leftOne) {  // 左右两侧均种花
                count += (right - left - 1) / 2;
            } else if (rightOne || leftOne) {   // 单侧种花
                count += (right - left) / 2;
            } else {    // 两侧均没有；全为0的情况
                // 种在奇数位置，才可以种植最多
                count += (right - left + 1) / 2;
            }
            left = right + 1;
        }

        return count >= n;
    }

    // 贪心算法
    // https://leetcode-cn.com/problems/can-place-flowers/solution/chong-hua-wen-ti-tan-xin-dang-qian-wei-z-959y/
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {

        int m = flowerbed.length;

        int count = 0;
        for (int i = 0; i < m; i++) {

            // 每一个为可以种植花的条件： 左右两侧必须为0 or 位于首尾特殊位置
            if (flowerbed[i] == 0
                    // 位于末尾 or 右侧为0
                    && (i == m - 1 || flowerbed[i + 1] == 0)
                    // 位于开头 or 左侧为0
                    && (i == 0 || flowerbed[i - 1] == 0)) {

                flowerbed[i] = 1;
                count++;
            }
        }
        return count >= n;
    }

    public static void main(String[] args) {
        Q32 q = new Q32();
//        int[] nums = new int[]{1, 0, 0, 0, 1};
        int[] nums = new int[]{0, 0};

        q.canPlaceFlowers(nums, 1);
    }
}


