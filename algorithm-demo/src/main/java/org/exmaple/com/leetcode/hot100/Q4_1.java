package org.exmaple.com.leetcode.hot100;

import java.util.*;

/**
 * 438. 找到字符串中所有字母异位词
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 */
public class Q4_1 {
    // 滑动窗口
    // 字母易位词，长度要保持一致，字母类型与数量均要保持一致

    // TODO 超时
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s.equals(p)) {
            ans.add(0);
            return ans;
        }
        if (s.length() < p.length()) {
            return ans;
        }

        HashSet<Integer> set = new HashSet<>();
        for (char c : p.toCharArray()) {
            set.add(c - '0');
        }


        char[] targetArr = p.toCharArray();
        Arrays.sort(targetArr);
        String target = new String(targetArr);

        int len = s.length();
        int width = p.length();
        int right = 0;
        int left = 0;
        while (left < len) {
            StringBuffer sb = new StringBuffer();
            while (sb.length() < width && right < len) {
                int nextChar = s.charAt(right);
                if (!set.contains(nextChar - '0')) {
                    left = right + 1;
                    right++;
                    sb = new StringBuffer();
                    continue;
                }
                sb.append(s.charAt(right));
                right++;
            }
            if (sb.length() == width) {
                char[] currentArr = sb.toString().toCharArray();
                Arrays.sort(currentArr);
                String cur = new String(currentArr);
                if (cur.equals(target)) {
                    ans.add(left);
                }
            }

            while (right < len && s.charAt(left + 1) == s.charAt(right + 1)) {
                left++;
                right++;
                ans.add(left);
            }
            if (right > len) {
                break;
            }
            left++;
        }

        return ans;
    }

    // 滑动窗口
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> list = new ArrayList<>();
        //char ss[] = s.toCharArray();
        char[] ss = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> winMap = new HashMap<>();
        // 统计目标字符串的各个字符的数量
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int l = 0;
        int r = 0;
        //记录有效字符个数
        int valid = 0;
        while (r < ss.length) {
            char c = ss[r];
            r++;
            // 限制必须为有效字符范围内
            if (map.containsKey(c)) {
                winMap.put(c, winMap.getOrDefault(c, 0) + 1);
                if (map.get(c).equals(winMap.get(c))) {
                    valid++;
                }
            }
            //当窗口大小和s1大小一样时，判断是否包含然后再压缩左边界
            while (r - l == p.length()) {
                // 确保各个字符数量对应的上
                if (valid == map.size()) {
                    list.add(l);    // 满足条件的起始索引
                }
                // 移动左侧索引
                char d = ss[l];
                l++;
                if (map.containsKey(d)) {
                    if (map.get(d).equals(winMap.get(d))) {
                        valid--;
                    }
                    winMap.put(d, winMap.getOrDefault(d, 0) - 1);
                }
            }
        }
        return list;
    }


    // 滑动窗口 + 双指针
    public List<Integer> findAnagrams3(String s, String p) {
        int n = s.length(), m = p.length();
        List<Integer> res = new ArrayList<>();
        if(n < m) return res;

        int[] pCnt = new int[26];
        int[] sCnt = new int[26];

        for(int i = 0; i < m; i++){
            pCnt[p.charAt(i) - 'a'] ++;
        }

        int left = 0;
        for(int right = 0; right < n; right++){
            int curRight = s.charAt(right) - 'a';
            sCnt[curRight]++;
            while(sCnt[curRight] > pCnt[curRight]){
                int curLeft = s.charAt(left) - 'a';
                sCnt[curLeft]--;
                left++;
            }
            if(right - left + 1 == m){
                res.add(left);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Q4_1 q = new Q4_1();
        q.findAnagrams("cbaebabacd", "abc");
    }
}


