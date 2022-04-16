package org.example.com.leetcode.year2022.month02;

import java.util.Arrays;

//1189. “气球” 的最大数量
public class Q13 {
    public int maxNumberOfBalloons(String text) {
        int[] ori = new int[26];
        int[] data = new int[26];
        int ans = Integer.MAX_VALUE;

        for (char c : "balloon".toCharArray()) {
            ori[c - 'a']++;
        }

        for (char c : text.toCharArray()) {
            data[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (ori[i] > 0) {
                if (data[i] < ori[i]) {
                    return 0;
                }
                ans = Math.min(ans, data[i] / ori[i]);
            }
        }

        return ans;
    }

    public int maxNumberOfBalloons2(String text) {
        int[] cnt = new int[5];
        for (char c : text.toCharArray()) {
            if (c == 'b') {
                cnt[0]++;
            } else if (c == 'a') {
                cnt[1]++;
            } else if (c == 'l') {
                cnt[2]++;
            } else if (c == 'o') {
                cnt[3]++;
            } else if (c == 'n') {
                cnt[4]++;
            }
        }
        cnt[2] /= 2;
        cnt[3] /= 2;

        return Arrays.stream(cnt).min().getAsInt();
    }
}
