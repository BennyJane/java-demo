package org.exmaple.com.leetcode.array.middle;


import java.util.ArrayList;
import java.util.List;

/**
 * 229. 求众数 II
 * https://leetcode-cn.com/problems/majority-element-ii/
 *
 * https://leetcode-cn.com/problems/majority-element-ii/solution/liang-fu-dong-hua-yan-shi-mo-er-tou-piao-fa-zui-zh/
 */
public class Q67 {
    // 摩尔投票法
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }

        //  初始化两个候选人，以及他们的投票
        int people1 = nums[0];
        int count1 = 0;
        int people2 = nums[0];
        int count2 = 0;

        for (int n : nums) {
            if (people1 == n) {
                count1++;
                continue;
            }
            if (people2 == n) {
                count2++;
                continue;
            }

            // 第一个候选人配对
            if (count1 == 0) {
                people1 = n;
                count1++;
                continue;
            }
            // 第一个候选人配对
            if (count2 == 0) {
                people2 = n;
                count2++;
                continue;
            }

            count1--;
            count2--;
        }
        // 计数阶段
        // 找到了两个候选人之后，需要确定票数是否满足大于 N/3
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (people1 == num) count1++;
            else if (people2 == num) count2++;
        }

        if (count1 > nums.length / 3) res.add(people1);
        if (count2 > nums.length / 3) res.add(people2);

        return res;
    }
}


