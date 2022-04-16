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


