package org.exmaple.com.leetcode.order.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class Q41 {

    List<String> ans;

    String[] key = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }
        dfs(digits, 0, new StringBuffer());
        return ans;
    }

    private void dfs(String digits, int index, StringBuffer sb) {
        if (index == digits.length() - 1) {
            ans.add(sb.toString());
        } else {
            int i = digits.charAt(index) - '0';
            String s = key[i - 2];
            for (char c : s.toCharArray()) {
                sb.append(c);
                dfs(digits, index + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }

        }
    }
}


