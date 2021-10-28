package benny.jane.com.leetcode.hot100;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 726. 原子的数量
 * https://leetcode-cn.com/problems/number-of-atoms/
 * <p>
 * 参考：394. 字符串解码 https://leetcode-cn.com/problems/decode-string/
 */
public class Q10 {
    // 逆序遍历
    public String countOfAtoms(String formula) {
        int len = formula.length();
        int index = len - 1;

        Map<String, Integer> map = new HashMap<>();
        Stack<String> stack = new Stack<>();
        while (index >= 0) {
            Character cur = formula.charAt(index);
            if (Character.isDigit(cur)) {   // 处理数字

            } else if (Character.isLetter(cur) && Character.isLowerCase(cur)) { // 处理复合原子名称

            } else if (Character.isLetter(cur) || cur == ')') { // 处理原子名称 与 右括号

            } else {

            }
        }

        return "";
    }
}


