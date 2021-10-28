package benny.jane.com.leetcode.array.simple;

/**
 * 495. 提莫攻击
 * https://leetcode-cn.com/problems/teemo-attacking/
 */
public class Q31 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = 0;
        int lastEnd = -1;    // 当前中毒状态结束时刻：输入数据可以为0
        for (int n : timeSeries) {
            if (n > lastEnd) {  // 下次中毒时，上次状态已经清除
                lastEnd = n - 1;    // 上次无毒状态设置为中毒前1秒
            }
            int end = n + duration - 1;
            if (end > lastEnd) {
                ans += (end - lastEnd);
                lastEnd = end;  // 需要更新结束时间
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Q31 q = new Q31();
        int[] nums = new int[]{1, 4};
//        int[] nums = new int[]{1, 2};
        q.findPoisonedDuration(nums, 2);
    }
}


