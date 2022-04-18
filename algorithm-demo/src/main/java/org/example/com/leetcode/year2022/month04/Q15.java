package org.example.com.leetcode.year2022.month04;

import java.util.*;

/**
 * 385. 迷你语法分析器
 * https://leetcode-cn.com/problems/mini-parser/
 */
public class Q15 {

    // TODO 必须设置为全局变量，因为部分调用没有返回该值
    private int index = 0;

    // 尾递归
    public NestedInteger deserialize(String s) {
        // TODO 递归处理模块1： [] 一对中括号
        if (s.charAt(index) == '[') {
            // 递归的核心
            index++;
            NestedInteger list = new NestedInteger();
            while (s.charAt(index) != ']') {
                NestedInteger nxt = deserialize(s);
                list.add(nxt);
                if (s.charAt(index) == ',') {
                    index++;
                }
            }
            index++;
            return list;
        } else {
            // 处理数字情况
            boolean negative = false;
            int num = 0;
            if (s.charAt(index) == '-') {
                negative = true;
                index++;
            }

            while (index < s.length() && Character.isDigit(s.charAt(index))) {
                num = num * 10 + s.charAt(index) - '0';
                index++;
            }
            if (negative) {
                num = -1 * num;
            }
            return new NestedInteger(num);
        }
    }


    public NestedInteger deserialize1(String s) {
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }

        Deque<NestedInteger> stack = new ArrayDeque<NestedInteger>();
        // 考虑正负数情况
        int num = 0;
        boolean negative = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '-') {
                negative = true;
            } else if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                // 压入队列头部
                // FIXME 遇到[, 栈顶压入数组
                stack.push(new NestedInteger());
            } else if (c == ',' || c == ']') {
                // 和数字相邻
                if (Character.isDigit(s.charAt(i - 1))) {
                    if (negative) {
                        num *= -1;
                    }
                    // peek获取队列头部元素,压入当前数字
                    // FIXME 添加到最近的[表示的数组中
                    stack.peek().add(new NestedInteger(num));
                }

                // 重新初始化
                num = 0;
                negative = false;
                // 处理数组中的最后一个数字元素
                if (c == ']' && stack.size() > 1) {
                    // 弹出栈顶元素
                    NestedInteger ni = stack.pop();
                    stack.peek().add(ni);
                }
            }
        }

        return stack.pop();
    }

    public NestedInteger deserialize2(String s) {
        if (s.charAt(0) != '[') {
            int num = Integer.parseInt(s);
            return new NestedInteger(num);
        }

        NestedInteger res = null;
        Deque<NestedInteger> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '[') {
                NestedInteger cur = new NestedInteger();
                stack.add(cur);
            } else if (c == ',') {
                int num = Integer.parseInt(sb.toString());
                NestedInteger cur = new NestedInteger(num);
                stack.add(cur);
                sb = new StringBuilder();
            } else if (c == ']') {
                if (sb.length() > 0) {
                    int num = Integer.parseInt(sb.toString());
                    NestedInteger cur = new NestedInteger(num);
                    stack.add(cur);
                }
                List<NestedInteger> tmp = new ArrayList<>();
                while (!stack.isEmpty()) {
                    NestedInteger ni = stack.pollLast();
                    if (ni.isInteger() || ni.getList().size() > 0) {
                        tmp.add(ni);
                    } else {
                        for (NestedInteger t : tmp) {
                            ni.add(t);
                        }
                        stack.add(ni);
                        break;
                    }
                }
            } else {
                sb.append(c);
            }
        }

        return stack.peek();
    }

    public static void main(String[] args) {
        Q15 q = new Q15();
//        q.deserialize("[123,[456,[789]]]");
    }
}


class NestedInteger {

    private List<NestedInteger> nestedList;
    private Integer num = null;

    // Constructor initializes an empty nested list.
    public NestedInteger() {
        nestedList = new ArrayList<>();
    }

    ;

    public NestedInteger(int value) {
        num = (Integer) value;
    }

    ;

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return num != null;
    }

    ;

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return num;
    }

    ;

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {
        this.num = value;
    }

    ;

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {
        nestedList.add(ni);
    }

    ;

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return nestedList;
    }

    ;

    @Override
    public String toString() {
        return "NestedInteger{" +
                "nestedList=" + nestedList +
                ", num=" + num +
                '}';
    }
}


/**
 * 341.扁平化嵌套列表迭代器
 */
// 递归是实现
class NestedIterator implements Iterator<Integer> {

    private List<Integer> list;
    private Iterator iterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = new ArrayList<>();
        dfs(nestedList);
//        tailDfs(nestedList, list);
        iterator = list.iterator();
    }

    private void dfs(List<NestedInteger> data) {
        for (NestedInteger ni : data) {
            if (ni.isInteger()) {
                list.add(ni.getInteger());
            } else {
                dfs(ni.getList());
            }
        }
    }

    // list 通过递归函数传递
    private void tailDfs(List<NestedInteger> data, List<Integer> arr) {
        for (NestedInteger ni : data) {
            if (ni.isInteger()) {
                arr.add(ni.getInteger());
            } else {
                tailDfs(ni.getList(), arr);
            }
        }
    }

    @Override
    public Integer next() {
        return (int) iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
}

/**
 * 439.三元表达式解析器
 * https://leetcode-cn.com/problems/ternary-expression-parser/
 * <p>
 * T?1:5 标准格式，即长度为5且包含？:时，可以简化
 * 非? : 长度均为1
 */
class Solution {
    // 使用栈
    public String parseTernary_Stack(String expression) {
        Deque<Character> sk = new ArrayDeque<>();
        // 从后往前遍历，遇到？符号，弹出栈内元素，进行运算并将结果，压入栈内
        int i = expression.length() - 1;
        while (i >= 0) {
            char c = expression.charAt(i);
            // 非？符号，全部压入栈中
            if (c != '?') {
                sk.addLast(c);
                i--;
                continue;
            }
            // c == ?时，处理[i-1, i, i +1, i+2, i++3] 5个位置组成的三元表达式
            // ? 左侧为条件
            char condition = expression.charAt(i - 1);
            char resTrue = sk.pollLast();   // 从队列末尾获取值
            // : 符号
            sk.poll();
            char resFalse = sk.pollLast();
            if (condition == 'T') {
                sk.addLast(resTrue);
            } else {
                sk.addLast(resFalse);
            }
            // 跳过当前c=?的索引
            i -= 2;
        }

        return String.valueOf(sk.poll());
    }

    /**
     * 栈：每次处理长度为2的字符组合，从右往左遍历
     */
    public String parseTernary_Stack2(String expression) {
        int n = expression.length();
        char[] array = expression.toCharArray();
        Deque<Character> sk = new ArrayDeque<>();
        // 不能取等号，array[0] 存储最后的结果
        for (int i = n - 1; i > 0; i -= 2) {
            char sign = array[i - 1];
            char c = array[i];
            if (sign == ':') {
                sk.addLast(c);
            } else {
                // sign = ?
                char condition = array[i - 2];
                if (condition == 'T') {
                    array[i - 2] = c;
                } else {
                    array[i - 2] = sk.peekLast();
                }
                sk.pollLast();
            }
        }

        return String.valueOf(array[0]);
    }

    // 递归处理：从左往右
    public String parseTernary(String expression) {
        int n = expression.length();
        int checkLevel = 0;
        // 找到每个? : 平衡区间后，缩小计算范围
        for (int i = 1; i < n; i++) {
            char c = expression.charAt(i);
            if (c == '?') checkLevel++;
            if (c == ':') checkLevel--;
            // 一定是在c=:的情况下，checkLevel 递减到0
            if (checkLevel == 0) {
                // 此时 c = :，处理前面完整的三元表达式，包含剪枝处理
                if (expression.charAt(0) == 'T') {
                    return parseTernary(expression.substring(2, i));
                } else {
                    return parseTernary(expression.substring(i + 1, n));
                }
            }
        }

        return expression;
    }

    public String dfs(String s, int left, int right) {
        if (left == right) return String.valueOf(s.charAt(left));

        int balance = 0;
        int balCnt = 0;
        int index = left;
        while (index <= right) {
            if (s.charAt(index) == '?') balance++;
            if (s.charAt(index) == ':') balance--;
            // 找到左侧第一组平衡位置: index指向：符号
            if (balance == 0 && balCnt == 1) {
                break;
            }
            if (balance == 0) {
                balCnt++;
            }
            index++;
        }
        char condition = s.charAt(left);
        return condition == 'T' ? dfs(s, left + 2, index - 1) : dfs(s, index+1, right);

    }

    public String parseTernary1(String expression) {
        return dfs(expression, 0, expression.length() -1);
    }

    // FIXME 从左往右遍历，无法正确分离每组三元表达式

    public static void main(String[] args) {
        Solution s = new Solution();
        String e = "T?T:F?T?1:2:F?3:4";
//        String e = "F?T:F?T?1:2:F?3:4";
        s.parseTernary_Stack(e);
    }
}

/**
 * 722. 删除注释
 * https://leetcode-cn.com/problems/remove-comments/
 */
class Solution2 {
    public List<String> removeComments(String[] source) {
        boolean inBlock = false;
        StringBuilder newline = new StringBuilder();
        List<String> ans = new ArrayList();
        for (String line : source) {
            int i = 0;
            char[] chars = line.toCharArray();
            if (!inBlock) newline = new StringBuilder();
            while (i < line.length()) {
                if (!inBlock && i + 1 < line.length() && chars[i] == '/' && chars[i + 1] == '*') {
                    inBlock = true;
                    i++;
                } else if (inBlock && i + 1 < line.length() && chars[i] == '*' && chars[i + 1] == '/') {
                    inBlock = false;
                    i++;
                } else if (!inBlock && i + 1 < line.length() && chars[i] == '/' && chars[i + 1] == '/') {
                    break;
                } else if (!inBlock) {
                    newline.append(chars[i]);
                }
                i++;
            }
            if (!inBlock && newline.length() > 0) {
                ans.add(new String(newline));
            }
        }
        return ans;
    }
}