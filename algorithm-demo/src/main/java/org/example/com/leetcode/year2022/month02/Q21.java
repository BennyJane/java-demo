package org.example.com.leetcode.year2022.month02;

import java.util.*;

public class Q21 {
    // 错误解答
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        int[] dp = new int[n];
        Arrays.fill(dp, 5);

        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (dominoes.charAt(i) == 'L') {
                dp[i] = -1;
                queue.offer(i);
            } else if (dominoes.charAt(i) == 'R') {
                dp[i] = 1;
                queue.offer(i);
            }
        }


        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int index = 0; index < size; index++) {
                int i = queue.poll();
                if (i == 0 && dp[i + 1] == 5) {
                    dp[i + 1] = dp[i];
                    queue.offerLast(i + 1);
                } else if (i == n - 1 && dp[i - 1] == 5) {
                    dp[i - 1] = dp[i];
                    queue.offerLast(i - 1);
                } else if (dp[i - 1] == 5 && dp[i + 1] == 5) {
                    dp[i - 1] = dp[i];
                    dp[i + 1] = dp[i];
                    queue.offerLast(i - 1);
                    queue.offerLast(i + 1);
                } else if (dp[i + 1] == 5) {
                    dp[i - 1] += dp[i];
                    dp[i + 1] = dp[i];
                    queue.offerLast(i + 1);
                } else if (dp[i - 1] == 5) {
                    dp[i - 1] = dp[i];
                    dp[i + 1] += dp[i];
                    queue.offerLast(i - 1);
                } else {
                    dp[i - 1] += dp[i];
                    dp[i + 1] += dp[i];
                }
            }
        }
        char[] data = new char[n];
        for (int i = 0; i < n; i++) {
            if (dp[i] == 0) {
                data[i] = '.';
            } else if (dp[i] < -1) {
                data[i] = 'L';
            } else {
                data[i] = 'R';
            }
        }

        return new String(data);
    }


    public String pushDominoes2(String dominoes) {
        String res = "";
        while (!res.equals(dominoes)) {
            res = dominoes;
            // 错误表达： 不需要转义
            // dominoes = dominoes.replace("R\\.L", "T");
            dominoes = dominoes.replace("R.L", "T");
            dominoes = dominoes.replace("R.", "RR");
            dominoes = dominoes.replace(".L", "LL");
            dominoes = dominoes.replace("T", "R.L");
        }
        return dominoes;
    }

    // 错误
    public String pushDominoes3(String dominoes) {
        int n = dominoes.length();
        char[] chars = dominoes.toCharArray();
        // i j 表示连续为. 的区间
        int i = 0, j = 0;
        // 区间左侧符号
        char left = 'L';

        while (i < n) {
            j = i;
            while (j < n && dominoes.charAt(j) == '.') {
                j++;
            }
            // 区间右侧符号
            char right = j < n ? dominoes.charAt(j) : 'R';
            // 左右区间符号相同
            if (left == right) {
                while (i < j) {
                    chars[i] = right;
                    i++;
                }
            } else if (left == 'R' && right == 'L') {
                // 左右区间符号 向内； 向外情况不需要讨论
                int end = j - 1;
                while (i < end) {
                    chars[i] = 'R';
                    chars[end] = 'L';
                    i++;
                    end--;
                }
            }
            // 更新区间，以及区间符号
            left = right;
            i = j + 1;
        }

        return new String(chars);
    }

    class Solution {
        public String pushDominoes(String dominoes) {
            int n = dominoes.length();
            Deque<Integer> queue = new ArrayDeque<Integer>();
            int[] time = new int[n];
            Arrays.fill(time, -1);
            List<Character>[] force = new List[n];
            for (int i = 0; i < n; i++) {
                force[i] = new ArrayList<Character>();
            }
            for (int i = 0; i < n; i++) {
                char f = dominoes.charAt(i);
                if (f != '.') {
                    queue.offer(i);
                    time[i] = 0;
                    force[i].add(f);
                }
            }

            char[] res = new char[n];
            Arrays.fill(res, '.');
            while (!queue.isEmpty()) {
                int i = queue.poll();
                if (force[i].size() == 1) {
                    char f = force[i].get(0);
                    res[i] = f;
                    int ni = f == 'L' ? i - 1 : i + 1;
                    if (ni >= 0 && ni < n) {
                        int t = time[i];
                        if (time[ni] == -1) {
                            queue.offer(ni);
                            time[ni] = t + 1;
                            force[ni].add(f);
                        } else if (time[ni] == t + 1) {
                            force[ni].add(f);
                        }
                    }
                }
            }
            return new String(res);
        }
    }
}
