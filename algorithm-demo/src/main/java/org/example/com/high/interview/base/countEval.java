package org.example.com.high.interview.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class countEval {
}

class Solution {
    // TODO 1^((0|0)|1)和1^(0|0|1)，两者答案显然一样，但是一个有括号一个没有括号,
    // 只算作一个答案

    /**
     * after 字符计算结果数量，重复累加
     * [before]: 1^0|0|1|0
     * [after]: 1^0|1|0
     * <p>
     * [before]: 1^0|0|1|0
     * [after]: 1^0|1|0
     */

    public int countEval(String s, int result) {
        if (s.length() == 1) {
            int res = s.charAt(0) - '0';
            System.out.println("res:   " + res);
            System.out.println("======================================");
            if (res == result) {
                return 1;
            }
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < s.length(); i++) {
            char sign = s.charAt(i);
            if (Character.isDigit(sign)) continue;
            int before = s.charAt(i - 1) - '0';
            int after = s.charAt(i + 1) - '0';
            int res = 0;
            if (sign == '&') {
                res = before & after;
            } else if (sign == '|') {
                res = before | after;
            } else if (sign == '^') {
                res = before ^ after;
            } else {
                continue;
            }

            String nxtS = s.substring(0, i - 1) + res + s.substring(i + 2);
            if (s == "1^0|0|1|0") {
                System.out.println("[before]: " + s);
                System.out.println("[after]: " + nxtS);
            }
            int tmp = countEval(nxtS, result);

            ans += tmp;
        }
        return ans;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
//        int res = s.countEval("1^0|0|1", 0);
//        int res = s.countEval("0&0&0&1^1|0", 1);
//        int res = s.countEval("0&0&0&1^1|0", 0);
        int res = s.countEval("1^0|0|1|0", 0);
        System.out.println(res);
    }
}

class Solution2 {

    // 记忆化搜索：需要记录字符串 + 结果值
    private static Map<String, Integer> cache = new HashMap<>();

    public int countEval(String s, int result) {
        if (s.length() == 1) {
            int res = s.charAt(0) - '0';
            if (res == result) {
                return 1;
            }
            return 0;
        }
        String key = s + "_" + result;
        if (cache.containsKey(key)) return cache.get(key);
        int ans = 0;
        for (int i = 1; i < s.length(); i += 2) {
            char sign = s.charAt(i);
            if (result == 0) {
                // TODO 起到减枝，筛选的功能
                if (sign == '&') {
                    //结果为0 有三种情况： 0 0, 0 1, 1 0
                    ans += countEval(s.substring(0, i), 0) * countEval(s.substring(i + 1), 0);
                    ans += countEval(s.substring(0, i), 1) * countEval(s.substring(i + 1), 0);
                    ans += countEval(s.substring(0, i), 0) * countEval(s.substring(i + 1), 1);
                }
                if (sign == '|') {
                    //结果为0 有一种情况： 0 0
                    ans += countEval(s.substring(0, i), 0) * countEval(s.substring(i + 1), 0);
                }
                if (sign == '^') {
                    //结果为0 有两种情况： 0 0, 1 1
                    ans += countEval(s.substring(0, i), 1) * countEval(s.substring(i + 1), 1);
                    ans += countEval(s.substring(0, i), 0) * countEval(s.substring(i + 1), 0);
                }
            } else {
                if (sign == '&') {
                    //结果为1 有一种情况： 1 1
                    ans += countEval(s.substring(0, i), 1) * countEval(s.substring(i + 1), 1);
                }
                if (sign == '|') {

                    //结果为1 有三种情况： 0 1, 1 0, 1 1
                    ans += countEval(s.substring(0, i), 0) * countEval(s.substring(i + 1), 1);
                    ans += countEval(s.substring(0, i), 1) * countEval(s.substring(i + 1), 1);
                    ans += countEval(s.substring(0, i), 1) * countEval(s.substring(i + 1), 0);
                }
                if (sign == '^') {
                    //结果为1 有两种情况： 0 1, 1 0
                    ans += countEval(s.substring(0, i), 1) * countEval(s.substring(i + 1), 0);
                    ans += countEval(s.substring(0, i), 0) * countEval(s.substring(i + 1), 1);
                }
            }
        }
        cache.put(key, ans);
        return ans;
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
//        int res = s.countEval("1^0|0|1", 0);
//        int res = s.countEval("0&0&0&1^1|0", 1);
//        int res = s.countEval("0&0&0&1^1|0", 0);
        int res = s.countEval("1^0|0|1|0", 0);
        System.out.println(res);
    }
}


class Solution3 {
    Integer[][][] memo;

    public int countEval(String s, int result) {
        int n = s.length();
        memo = new Integer[n][n][2];
        return dfs(0, n - 1, s, result);
    }

    int dfs(int l, int r, String s, int result) { //区间[l, r]求result的括号方案数
        if (l > r) return 0;
        if (l == r) {
            return (s.charAt(l) - '0') == result ? 1 : 0;
        }
        // FIXME 默认值是null？？
        if (memo[l][r][result] != null) return memo[l][r][result];
        int ans = 0;
        for (int i = l; i <= r; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) continue;
            if (result == 0) {
                if (c == '&')
                    ans += dfs(l, i - 1, s, 0) * dfs(i + 1, r, s, 0) + dfs(l, i - 1, s, 0) * dfs(i + 1, r, s, 1) + dfs(l, i - 1, s, 1) * dfs(i + 1, r, s, 0); //00、01、10
                if (c == '|') ans += dfs(l, i - 1, s, 0) * dfs(i + 1, r, s, 0); //00
                if (c == '^')
                    ans += dfs(l, i - 1, s, 0) * dfs(i + 1, r, s, 0) + dfs(l, i - 1, s, 1) * dfs(i + 1, r, s, 1); //00、11
            } else {
                if (c == '&') ans += dfs(l, i - 1, s, 1) * dfs(i + 1, r, s, 1); //11
                if (c == '|')
                    ans += dfs(l, i - 1, s, 0) * dfs(i + 1, r, s, 1) + dfs(l, i - 1, s, 1) * dfs(i + 1, r, s, 0) + dfs(l, i - 1, s, 1) * dfs(i + 1, r, s, 1); //01、10、11
                if (c == '^')
                    ans += dfs(l, i - 1, s, 0) * dfs(i + 1, r, s, 1) + dfs(l, i - 1, s, 1) * dfs(i + 1, r, s, 0); //10、01
            }
        }
        return memo[l][r][result] = ans;
    }
}


class Solution4 {
    public int countEval(String s, int result) {
        //特例
        if (s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return (s.charAt(0) - '0') == result ? 1 : 0;
        }
        char[] ch = s.toCharArray();
        //定义状态: 给定区间上执行结果只有两种
        int[][][] dp = new int[ch.length][ch.length][2];
        //base case：长度为1的计算结果
        // 只处理left <= right的范围，只用到一半区间
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '0' || ch[i] == '1') {
                dp[i][i][ch[i] - '0'] = 1;
            }
        }
        //套区间dp模板
        //枚举区间长度len，跳步为2，一个数字一个符号
        for (int len = 2; len <= ch.length; len += 2) {
            //枚举区间起点，数字位，跳步为2
            for (int i = 0; i <= ch.length - len; i += 2) {
                //区间终点，数字位
                int j = i + len;
                //枚举分割点，三种 '&','|', '^'，跳步为2
                for (int k = i + 1; k <= j - 1; k += 2) {
                    if (ch[k] == '&') {
                        //结果为0 有三种情况： 0 0, 0 1, 1 0
                        //结果为1 有一种情况： 1 1
                        dp[i][j][0] += dp[i][k - 1][0] * dp[k + 1][j][0] + dp[i][k - 1][0] * dp[k + 1][j][1] + dp[i][k - 1][1] * dp[k + 1][j][0];
                        dp[i][j][1] += dp[i][k - 1][1] * dp[k + 1][j][1];
                    }
                    if (ch[k] == '|') {
                        //结果为0 有一种情况： 0 0
                        //结果为1 有三种情况： 0 1, 1 0, 1 1
                        dp[i][j][0] += dp[i][k - 1][0] * dp[k + 1][j][0];
                        dp[i][j][1] += dp[i][k - 1][0] * dp[k + 1][j][1] + dp[i][k - 1][1] * dp[k + 1][j][0] + dp[i][k - 1][1] * dp[k + 1][j][1];
                    }
                    if (ch[k] == '^') {
                        //结果为0 有两种情况： 0 0, 1 1
                        //结果为1 有两种情况： 0 1, 1 0
                        dp[i][j][0] += dp[i][k - 1][0] * dp[k + 1][j][0] + dp[i][k - 1][1] * dp[k + 1][j][1];
                        dp[i][j][1] += dp[i][k - 1][1] * dp[k + 1][j][0] + dp[i][k - 1][0] * dp[k + 1][j][1];
                    }
                }
            }
        }
        return dp[0][ch.length - 1][result];
    }

    // FIXME 错误！！！ 状态转移方程错误，前一个状态还没有计算出来
    public int countEval2(String s, int result) {
        if (s.length() == 1) {
            return (s.charAt(0) - '0') == result ? 1 : 0;
        }
        char[] ch = s.toCharArray();
        int n = ch.length;
        //定义状态: 给定区间上执行结果只有两种
        int[][][] dp = new int[n][n][2];
        //base case：长度为1的计算结果
        // 只处理left <= right的范围，只用到一半区间
        for (int i = 0; i < n; i++) {
            if (ch[i] == '0' || ch[i] == '1') {
                dp[i][i][ch[i] - '0'] = 1;
            }
        }

        // TODO 错误思想：直接单个起点，遍历所有情况
        for (int i = 0; i < n; i += 2) {
            for (int j = i + 2; j < n; j += 2) {
                // k 枚举符号位置
                for (int k = i + 1; k <= j - 1; k += 2) {
                    char kChar = ch[k];
                    switch (kChar) {
                        case '&':
                            // & 计算得到0的情况： 00，01，10
                            dp[i][j][0] += dp[i][k - 1][0] * dp[k + 1][j][0];
                            dp[i][j][0] += dp[i][k - 1][1] * dp[k + 1][j][0];
                            dp[i][j][0] += dp[i][k - 1][0] * dp[k + 1][j][1];
                            // & 计算得到1的情况： 11
                            dp[i][j][1] += dp[i][k - 1][1] * dp[k + 1][j][1];
                            break;
                        case '|':
                            // | 计算得到0的情况： 00
                            dp[i][j][0] += dp[i][k - 1][0] * dp[k + 1][j][0];
                            // | 计算得到1的情况： 11，01，10
                            dp[i][j][1] += dp[i][k - 1][1] * dp[k + 1][j][1];
                            dp[i][j][1] += dp[i][k - 1][1] * dp[k + 1][j][0];
                            dp[i][j][1] += dp[i][k - 1][0] * dp[k + 1][j][1];
                            break;
                        case '^':
                            // 计算得到0的情况： 01， 10
                            dp[i][j][0] += dp[i][k - 1][0] * dp[k + 1][j][1];
                            dp[i][j][0] += dp[i][k - 1][1] * dp[k + 1][j][0];
                            // 计算得到1的情况： 00， 11
                            dp[i][j][1] += dp[i][k - 1][1] * dp[k + 1][j][1];
                            dp[i][j][1] += dp[i][k - 1][0] * dp[k + 1][j][0];
                            break;
                    }
                }
            }
        }
        System.out.println(dp);
        return dp[0][ch.length - 1][result];
    }

    public static void main(String[] args) {
        Solution4 s = new Solution4();
        s.countEval2("1^0|0|1|0", 0);
    }
}

class Solution6 {
    public int countEval(String s, int result) {
        int[] res = dfs(s);
        return res[result];
    }

    // 直接统计String对应的List<Integer>集合，会造成内存溢出
    // TODO 修改缓存方案：只统计结果0、1的计算数量
    private Map<String, int[]> map = new HashMap<>();
    private int[] dfs(String s) {
        int n = s.length();
        int[] ans = new int[2];

        if (n == 1) {
            int cur = s.charAt(0) - '0';
            ans[cur]++;
            return ans;
        }

        if (map.containsKey(s)) return map.get(s);

        for (int i = 1; i < n; i += 2) {
            char c = s.charAt(i);
            int[] left = dfs(s.substring(0, i));
            int[] right = dfs(s.substring(i + 1, n));

            if (c == '&') {
                ans[0] += left[0] * right[0] + left[1] * right[0] + left[0] * right[1];
                ans[1] += left[1] * right[1];
            }
            if (c == '|') {
                ans[0] += left[0] * right[0];
                ans[1] += left[1] * right[1] + left[1] * right[0] + left[0] * right[1];
            }
            if (c == '^') {
                ans[0] += left[0] * right[0] + left[1] * right[1];
                ans[1] += left[1] * right[0] + left[0] * right[1];
            }
        }
        map.put(s, ans);

        return ans;
    }


}

//241. 为运算表达式设计优先级
class Solution5 {
    // 数字长度不定，需要处理
    public List<Integer> diffWaysToCompute(String expression) {
        int n = expression.length();
        List<Integer> ans = new ArrayList<Integer>();
        // 长度为0： 不存在长度为0的情况
        // 判断是否包含特殊符号 ==》 判读是否为纯数字
        // TODO 效率非常低
        if (expression.split("\\+|-|\\*").length == 1) {
            ans.add(Integer.parseInt(expression));
            return ans;
        }


        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                continue;
            }
            List<Integer> left = diffWaysToCompute(expression.substring(0, i));
            List<Integer> right = diffWaysToCompute(expression.substring(i + 1, n));
            for (int l : left) {
                for (int r : right) {
                    switch (c) {
                        case '+':
                            ans.add(l + r);
                            break;
                        case '-':
                            ans.add(l - r);
                            break;
                        case '*':
                            ans.add(l * r);
                            break;
                    }
                }
            }
        }

        return ans;
    }

    public List<Integer> diffWaysToCompute1(String expression) {
        List<Integer> ans = new ArrayList<>();

        int n = expression.length();
        // 获取首个数字
        int num = 0;
        boolean isNum = true;
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                if (isNum) {
                    num = num * 10 + c - '0';
                }
                continue;
            }
            // 包含运算符
            isNum = false;
            List<Integer> left = diffWaysToCompute1(expression.substring(0, i));
            List<Integer> right = diffWaysToCompute1(expression.substring(i + 1, n));
            for (int l : left) {
                for (int r : right) {
                    if (c == '+') {
                        ans.add(l + r);
                    } else if (c == '-') {
                        ans.add(l - r);
                    } else {
                        ans.add(l * r);
                    }
                }
            }
        }

        // 如果输入字符串为纯数字，直接返回该数字
        if (isNum) {
            ans.add(num);
        }

        return ans;
    }


    private static Map<String, List<Integer>> cache = new HashMap<>();

    public List<Integer> diffWaysToCompute2(String expression) {
        List<Integer> ans = new ArrayList<>();
        if (cache.containsKey(expression)) {
            return cache.get(expression);
        }
        ;
        // 获取首个数字
        int num = 0;
        boolean isNum = true;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                if (isNum) {
                    num = num * 10 + c - '0';
                }
                continue;
            }
            // 包含运算符
            isNum = false;
            List<Integer> left = diffWaysToCompute1(expression.substring(0, i));
            List<Integer> right = diffWaysToCompute1(expression.substring(i + 1, expression.length()));
            for (int l : left) {
                for (int r : right) {
                    if (c == '+') {
                        ans.add(l + r);
                    } else if (c == '-') {
                        ans.add(l - r);
                    } else {
                        ans.add(l * r);
                    }
                }
            }
        }

        // 如果输入字符串为纯数字，直接返回该数字
        if (isNum) {
            ans.add(num);
        }

        cache.put(expression, ans);
        return ans;
    }
}