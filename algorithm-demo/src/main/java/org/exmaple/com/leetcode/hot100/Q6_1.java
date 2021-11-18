package org.exmaple.com.leetcode.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. 给表达式添加运算符
 */
public class Q6_1 {

    // 递归：考虑剪枝
    // TODO 还需要考虑符号运算的优先级情况： 先算乘法，再算加减法
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        // 先处理特殊情况
        if (num == null || num.length() == 0) return rst;
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }

    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed) {
        if (pos == num.length()) {  // 递归的终止条件
            if (target == eval)
                rst.add(path);
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            // 跳过前置位为0的情况
            if (i != pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) {     // 特殊处理起始条件
                helper(rst, path + cur, num, target, i + 1, cur, cur);
            } else {
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur, cur);

                helper(rst, path + "-" + cur, num, target, i + 1, eval - cur, -cur);
                // TODO 处理乘法的特殊技巧： 记录前一个值
                helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur);
            }
        }
    }

    public static void main(String[] args) {
        Q6_1 q = new Q6_1();
//        q.addOperators("123", 6);
//        q.addOperators("3456237490", 9191);
        q.addOperators("232", 8);
    }
}


