package org.example.com.leetcode.array.middle;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 784. 字母大小写全排列
 * https://leetcode-cn.com/problems/letter-case-permutation/
 */
public class Q60 {

    private Set<String> ans;

    public List<String> letterCasePermutation(String s) {
        ans = new HashSet<>();
        StringBuffer sb = new StringBuffer();
        dfs(s, 0, sb);
        return new ArrayList<>(ans);
    }

    private void dfs(String s, int i, StringBuffer sb) {
        if (i >= s.length()) {
            ans.add(sb.toString());
        } else {
            char c = s.charAt(i);
            sb.append(Character.toLowerCase(c));    // 确保为小写
            dfs(s, i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
            if (Character.isDigit(c)) {  // 区别数值与字符串
                sb.append(Character.toUpperCase(c));
                dfs(s, i + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Q60 q = new Q60();
//        q.letterCasePermutation("a1b2");
        q.letterCasePermutation("c");
    }
}


