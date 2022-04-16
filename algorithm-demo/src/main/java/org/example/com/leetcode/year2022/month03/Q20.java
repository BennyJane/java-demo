package org.example.com.leetcode.year2022.month03;

import java.util.*;

// 2039. 网络空闲的时刻
public class Q20 {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int ans = 0;
        // 构建图：保存相邻点关系
        // TODO 优化：使用嵌套数据数据结构
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] arr : edges) {
            int x = arr[0], y = arr[1];
            if (!graph.containsKey(x)) {
                graph.put(x, new ArrayList<>());
            }
            List<Integer> tmp1 = graph.get(x);
            tmp1.add(y);
            graph.put(x, tmp1);

            if (!graph.containsKey(y)) {
                graph.put(y, new ArrayList<>());
            }
            List<Integer> tmp2 = graph.get(y);
            tmp2.add(x);
            graph.put(y, tmp2);
        }

        int n = patience.length;
        // 记录已经访问过的点
        boolean[] visited = new boolean[n];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(0);
        visited[0] = true;
        // 记录路径长度
        int path = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            path++;
            int doublePath = 2 * path;
            for (int i = 0; i < size; i++) {
                int start = deque.pollFirst();
                for (int end : graph.get(start)) {
                    if (visited[end]) {
                        continue;
                    }
                    int p = patience[end];
                    if (doublePath <= p) {  // 只发送一次消息
                        ans = Math.max(ans, doublePath);
                    } else {
                        // 发送多次消息：计算最后一次消息
                        int cnt = doublePath % p == 0 ? doublePath / p - 1 : doublePath / p;
                        int cost = cnt * p + doublePath;
                        ans = Math.max(ans, cost);
                    }
                    deque.offer(end);
                    visited[end] = true;
                }
            }
        }
        return ans + 1;
    }

    public int networkBecomesIdle2(int[][] edges, int[] patience) {
        int n = patience.length, ans = 0;
        // FIXME 可以使用 List<Integer>[] graph 来定义
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] uv : edges) {
            // 记录双向关系
            graph.get(uv[0]).add(uv[1]);
            graph.get(uv[1]).add(uv[0]);
        }

        boolean[] visited = new boolean[n];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(0);
        visited[0] = true;
        int dist = 0;
        while (!deque.isEmpty()) {
            dist++;
            int doubleDist = 2 * dist;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int start = deque.poll();
                for (int end : graph.get(start)) {
                    if (visited[end]) {
                        continue;
                    }
                    // FIXME 技巧：长度 -1，避免分类讨论
                    int time = patience[end] * ((doubleDist - 1) / patience[end]) + doubleDist + 1;
                    ans = Math.max(time, ans);
                    deque.add(end);
                    visited[end] = true;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Q20 q = new Q20();
        int[][] array = {
                {0, 1},
                {1, 2}
        };
        int[] p = {0, 2, 1};
        q.networkBecomesIdle(array, p);
    }
}
