package org.example.com.leetcode.order.middle;

import com.sun.xml.internal.txw2.annotation.XmlNamespace;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/
 */
public class Q42 {
    // 回溯法
    // 优先放左括号，放右括号时，保证当前右括号数量少于左括号

    // TODO 考虑多种解法
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(ans, new StringBuffer(), 0,0,n);
        return ans;
    }

    public void dfs(List<String> ans, StringBuffer cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append("(");
            dfs(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }

        if (close < open) {
            cur.append(")");
            dfs(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}


