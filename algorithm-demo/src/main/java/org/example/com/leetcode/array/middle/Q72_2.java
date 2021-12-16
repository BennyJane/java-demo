package org.example.com.leetcode.array.middle;

/**
 * 457. 环形数组是否存在循环
 * https://leetcode-cn.com/problems/circular-array-loop/
 */
public class Q72_2 {
    // 整个循环的数值和为 n or -n
    // 假设存在循环，那么循环过程中的索引固定，从任意一个索引开始，都遍历相同的索引(相同的过程)

    // TODO 无法构成循环的条件： 下一个元素符号相反(一正一负) ； 当前索引的值移动后回到自身位置(长度没有大于1)
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; ++i) {
            //修改索引值 保证其 < n
            nums[i] = nums[i] % n;
        }

        for (int i = 0; i < n; ++i) {
            int curNum = nums[i];
            //跳过之前尝试过的
            if (curNum >= n) continue;

            int j = i;
            int flag = n + i;   // 所有访问过的值，直接标记为大于+n的索引形式
            int last = j;   // 回到循环起点前的一个索引
            while (nums[j] < n) {
                //方向不一致 直接退出
                if (curNum * nums[j] < 0) break;
                // +n 确保索引为正数，避免报错； 数据处理的措施
                int nextIndex = (j + nums[j] + n) % n;
                nums[j] = flag; // 标记该索引已经被使用
                last = j;
                j = nextIndex;
            }
            // 确保回到起始点，且 不是直接回到初始点（确保长度大于1）
            if (nums[j] == flag && j != last) return true;
        }

        return false;
    }
}


