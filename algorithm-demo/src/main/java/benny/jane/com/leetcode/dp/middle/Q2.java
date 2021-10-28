package benny.jane.com.leetcode.dp.middle;


import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 406. 根据身高重建队列
 * https://leetcode-cn.com/problems/queue-reconstruction-by-height/
 */
public class Q2 {
    public int[][] reconstructQueue(int[][] people) {
        // 身高降序，前n值升序
        Arrays.sort(people,
                (m, n) -> n[0] == m[0] ? m[1] - n[1] : n[0] - m[0]);
        int len = people.length;
        if (len < 2) {
            return people;
        }
        for (int i = 1; i < len; i++) {
            int count = 0;
            int k = people[i][1];   // 前面最多数量为k
            int flagIndex = i;
            for (int j = 0; j < i; j++) {
                if (people[j][0] >= people[i][0]) {
                    count++;
                    if (count > k) {
                        flagIndex = j;
                        break;
                    }
                }
            }
            // 插入数组，全部往后移动1个位置
            int[] cur = people[i];
            int[] pre = people[flagIndex];
            for (int left = flagIndex; left < i; left++) {
                int[] temp = people[left + 1];
                people[left + 1] = pre;
                pre = temp;
            }
            people[flagIndex] = cur;
        }
        return people;
    }


    public static void main(String[] args) {
        Q2 q = new Q2();
        int[][] people = new int[][]{
                {7, 0},
                {4, 4},
                {7, 1},
                {5, 0},
                {6, 1},
                {5, 2},
        };
        q.reconstructQueue(people);
    }
}