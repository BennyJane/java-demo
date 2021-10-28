package benny.jane.com.leetcode.hot100;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 282. 给表达式添加运算符
 */
public class Q6 {

    private List<String> ans;

    // 递归：考虑剪枝
    // TODO 还需要考虑符号运算的优先级情况： 先算乘法，再算加减法
    // 解题失败
    public List<String> addOperators(String num, int target) {
        ans = new ArrayList<>();

        for (int i = 1; i <= num.length(); i++) {
            try {

                long cur = Integer.parseInt(num.substring(0, i));
                dfs(num, target, i, cur, cur + "");
            } catch (NumberFormatException e) {
                return new ArrayList<>();
            }
        }

        return ans;
    }

    private void dfs(String num, int target, int left, long res, String pre) {
        if (left >= num.length()) {
            if (res == target) {
                ans.add(pre);
            }
        } else {
            for (int i = left + 1; i <= num.length(); i++) {
                int cur = Integer.parseInt(num.substring(left, i));
                dfs(num, target, i, res + cur, pre + "+" + cur);
                dfs(num, target, i, res - cur, pre + "-" + cur);
                dfs(num, target, i, res * cur, pre + "*" + cur);
            }
        }

    }

    public static void main(String[] args) {
        Q6 q = new Q6();
//        q.addOperators("123", 6);
//        q.addOperators("3456237490", 9191);
        q.addOperators("232", 8);
    }
}


