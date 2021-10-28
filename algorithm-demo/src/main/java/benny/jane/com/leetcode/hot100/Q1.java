package benny.jane.com.leetcode.hot100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class Q1 {
    // 哈希表
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        int len = s.length();
        char[] arr = s.toCharArray();
        HashSet<Character> set = new HashSet<>();
        // 逐个字符作为起点类遍历
        for (int i = 0; i < len; i++) {
            char cur = arr[i];
            set.add(cur);
            int right = i + 1;
            while (right < len && !set.contains(arr[right])) {
                set.add(arr[right]);
                right++;
            }
            ans = Math.max(ans, set.size());
            if (right > len) {
                break;
            }
        }

        return ans;
    }

    // TODO: 没有通过所有测试用例
    public int lengthOfLongestSubstring2(String s) {
        int ans = 0;
        int len = s.length();
        char[] arr = s.toCharArray();
        HashSet<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        while (right < len) {
            while (right < len && !set.contains(arr[right])) {
                set.add(arr[right]);
                right++;
            }
            ans = Math.max(ans, set.size());
            if (right >= len) {
                break;
            }
            if (arr[left] == arr[right]) {
                set.remove(arr[left]);
                left++;
            } else {
                while (left < right && arr[left] != arr[right]) {
                    set.remove(arr[left]);
                    left++;
                }
                set.remove(arr[left]);
            }
        }

        return ans;
    }


    // 标准答案: 滑动窗口
    public int lengthOfLongestSubstring3(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;

        // 每次滑动窗口前，只需要删除一个左字符
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                // 删除上一个左指针对应的字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
            // 可有可无，对效率影响不大
            if (rk + 1 >= n) {
                break;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Q1 q = new Q1();
//        q.lengthOfLongestSubstring("abcabcbb");
//        q.lengthOfLongestSubstring2("abcabcbb");
//        q.lengthOfLongestSubstring2("pwwkew");
//        q.lengthOfLongestSubstring2("dvdf");
        q.lengthOfLongestSubstring2("aabaab!bb");
    }

}


