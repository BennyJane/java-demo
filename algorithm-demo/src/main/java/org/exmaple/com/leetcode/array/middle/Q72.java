package org.exmaple.com.leetcode.array.middle;

/**
 * 457. 环形数组是否存在循环
 * https://leetcode-cn.com/problems/circular-array-loop/
 */
public class Q72 {
    // 整个循环的数值和为 n or -n
    // 假设存在循环，那么循环过程中的索引固定，从任意一个索引开始，都遍历相同的索引(相同的过程)

    // TODO 无法构成循环的条件： 下一个元素符号相反(一正一负) ； 当前索引的值移动后回到自身位置(长度没有大于1)
    public boolean circularArrayLoop(int[] nums) {
        int allZeroNum = 0; // 统计所有不符合条件的索引
        while (true) {  // 主循环不能取消
            int currentZeroNum = 0;
            // 遍历所有索引，将每个点向下移动一步，然后标记不符合条件的索引
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) { // 跳过已经被标记为不符合条件的索引
                    continue;
                }

                // 计算移动后的索引：考虑正负属性
                int newIndex = (i + nums[i]) % nums.length;
                if (newIndex < 0) { // 负值需要更新索引
                    newIndex = nums.length + newIndex;
                }

                if (newIndex == i   // 返回自身
                        || nums[newIndex] == 0  // 已经被标记不符合条件
                        || nums[i] * nums[newIndex] < 0) {  //  一正一负
                    allZeroNum++;
                    currentZeroNum++;
                    nums[i] = 0;
                }
            }

            if (currentZeroNum == 0 // 表示所有索引均可以移动下一个位置：TODO 如何证明此条件下就一定存在循环？
                    || allZeroNum == nums.length) {
                break;
            }
        }

        return nums.length != allZeroNum;
    }
}


