package benny.jane.com.leetcode.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * 647. 回文子串
 * https://leetcode-cn.com/problems/palindromic-substrings/
 */
public class Q2_2 {
    public int countSubstrings(String s) {
        int count = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        char[] arr = s.toCharArray();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            map.put(arr[i], i);
        }

        for (int i = 0; i < len; i++) {
            int maxRight = map.get(arr[i]);
            for (int j = maxRight; j >= i; j--) {
                int start = i;
                int end = j;
                while (start <= end && arr[start] == arr[end]) {
                    start++;
                    end--;
                }
                if (start > end) {  // 满足回文字符串特征
                    count++;
                }
            }
        }

        return count;
    }

    // 耗时非常短
    // 返回数量
    public int countSubstrings2(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

    // 返回最大长度
    public int countSubstringsToMax(String s) {
        int ans = 0;
        int len = s.length();
        // （len *2 -1） / 2: 偶数长度，中心位置左侧索引
        for (int i = 0; i < len * 2 - 1; i++) {
            int left = i / 2;
            int right = i / 2 + i % 2;
            int childLen = 0;   // 计算当前回文串长度
            while (left >= 0 && right < len
                    && s.charAt(left) == s.charAt(right)) {
                childLen += left == right ? 1 : 2;
                left--;
                right++;
            }
            ans = Math.max(ans, childLen);
        }

        return ans;
    }
}


