package benny.jane.com.leetcode.array.middle;

/**
 * 621. 任务调度器
 * https://leetcode-cn.com/problems/task-scheduler/
 */
public class Q63 {
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }

        char[] count = new char[26];    // 统计26个字母的数量
        for (char c : tasks) {
            int index = c - 'a';
            count[index]++;
        }

        int max = Integer.MIN_VALUE;
        for (int num : count) {
            max = Math.max(num, max);
        }

        int maxCount = 0;
        for (int i : count) {
            if (i == max) {
                maxCount++;
            }
        }

        int ans = (n + 1) * (max - 1) + maxCount;

        return Math.max(ans, tasks.length);
    }
}


