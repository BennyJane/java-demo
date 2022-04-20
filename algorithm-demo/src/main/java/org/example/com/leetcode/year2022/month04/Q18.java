package org.example.com.leetcode.year2022.month04;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/lexicographical-numbers/
 * 386.字典序排数
 */
public class Q18 {

    private List<Integer> ans;

    public List<Integer> lexicalOrder(int n) {
        ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            dfs(i, n);
        }
        return ans;
    }

    // 简化递归逻辑，但增加部分递归深度，效率降低
    private void dfs(int index, int n) {
        if (index > n) {
            return;
        }
        ans.add(index);
        for (int i = 0; i <= 9; i++) {
            int tmp = index * 10 + i;
            // 将tmp的判断，放入递归函数的入口来判断
            dfs(tmp, n);
        }

    }

    public static void main(String[] args) {
        Q18 q = new Q18();
        q.lexicalOrder(34);
    }
}

class Question2 {

    public List<Integer> lexicalOrder(int n) {
        Integer[] ans = new Integer[n];
        int num = 1;
        for (int i = 0; i < n; i++) {
            ans[i] = num;
            if (num * 10 <= n) {
                // 优先处理 X0, X00, X000...情况
                num *= 10;
            } else {
                while (num % 10 == 9 || num + 1 > n) {
                    num /= 10;
                }
                // 在X000 <= n基础上，递增末尾数字
                num += 1;
            }
        }

        return Arrays.asList(ans);
    }
}

// FIXME 代码啰嗦，但是执行效率最高
class Question3 {

    private List<Integer> ans;

    public List<Integer> lexicalOrder(int n) {
        ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (i > n) {
                break;
            }
            ans.add(i);
            dfs(i, n);
        }
        return ans;
    }
    private void dfs(int index, int n) {
        if (index > n) {
            return;
        }

        for (int i = 0; i <= 9; i++) {
            int tmp = index * 10 + i;
            if (tmp > n) {
                break;
            }
            if (tmp <= n) {
                ans.add(tmp);
                dfs(tmp, n);
            }
        }
    }
}



class Solution1 {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans=new ArrayList<>();
        for(int i=1;i<=n;i++){ans.add(i);}
        Collections.sort(ans, Comparator.comparing(String::valueOf));
        //Collections.sort(ans, (a ,b ) -> String.valueOf(a).compareTo(String.valueOf(b)));
        return ans;
    }
}