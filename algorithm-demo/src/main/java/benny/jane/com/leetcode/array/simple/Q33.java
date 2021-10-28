package benny.jane.com.leetcode.array.simple;

import com.benny.learning.algorithm.Q;

import java.util.Stack;

/**
 * 735. 行星碰撞
 * https://leetcode-cn.com/problems/asteroid-collision/
 * <p>
 * 值得写一个解法： 更方便的是栈结构解法
 */
public class Q33 {
    // 滑动窗口
    public int[] asteroidCollision(int[] asteroids) {
        int len = asteroids.length;
        int right = 0;
        int count = 0;  // 记录爆炸的数量
        while (right < len) {
            while (right < len && asteroids[right] > 0) {
                //  寻找下一个负数
                right++;
            }
            if (right >= len) {
                break;
            }
            int pre = right;
            while (pre >= 0 && asteroids[pre] <= 0) {
                pre--;
            }
            if (pre < 0) {  // 前序没有正数
                right++;   // 开始寻找下一个负数
            } else {
                int x = Math.abs(asteroids[pre]);
                int y = Math.abs(asteroids[right]);
                if (x == y) {
                    asteroids[pre] = 0;  // 标记为0
                    asteroids[right] = 0;  // 标记为0
                    right++;
                    count += 2;
                } else if (x > y) {
                    asteroids[right] = 0;
                    right++;
                    count++;
                } else {
                    asteroids[pre] = 0; // 继续往左寻找正数
                    count++;
                }
            }
        }
        int[] ans = new int[len - count];
        int index = 0;
        for (int n : asteroids) {
            if (n != 0) {
                ans[index] = n;
                index++;
            }
        }
        return ans;
    }


    // TODO goto语法
    public int[] asteroidCollision2(int[] asteroids) {
        Stack<Integer> stack = new Stack();
        for (int ast : asteroids) {
            collision:
            {
                // 行星小于0，栈顶元素大于0
                while (!stack.isEmpty() && ast < 0 && 0 < stack.peek()) {
                    // 如果右侧的行星大 则 栈顶元素毁灭
                    if (stack.peek() < -ast) {
                        stack.pop();
                        // 重复执行
                        continue;
                    } else if (stack.peek() == -ast) {
                        // 两者相等，同时毁灭
                        stack.pop();
                    }
                    // 左侧小行星大，则跳出循环，负数星球自己爆炸
                    break collision;
                }
                // 负数小行星为爆炸，需要入栈
                stack.push(ast);
            }
        }

        int[] ans = new int[stack.size()];
        for (int t = ans.length - 1; t >= 0; --t) {
            ans[t] = stack.pop();
        }
        return ans;
    }


    public static void main(String[] args) {
        Q33 q = new Q33();
//        int[] nums = new int[]{8, -8};
//        int[] nums = new int[]{10, 2, -5};
        int[] nums = new int[]{-2, -1, 1, 2};
        q.asteroidCollision(nums);
    }
}


