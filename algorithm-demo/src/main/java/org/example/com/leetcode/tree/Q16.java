package org.example.com.leetcode.tree;

import java.util.*;

//2049. 统计最高分的节点数目
public class Q16 {
    // FIXME： 错误
    public int countHighestScoreNodes2(int[] parents) {
        int n = parents.length;
        // 计算每个节点为根节点的树的节点个数
        int[] cnt = new int[n];
        Arrays.fill(cnt, 1);

        // 统计每个节点的子节点
        Map<Integer, List<Integer>> map = new HashMap<>();

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            int p = parents[i];
            if (p >= 0) {
                visited[p] = true;
                List tmp = map.getOrDefault(p, new ArrayList());
                tmp.add(i);
                map.put(p, tmp);
            }
        }

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                deque.add(i);
                visited[i] = true;
            }
        }

        // FIXME: 从下往上的层序遍历BUG，不同深度的叶子节点不能保证同时到达父节点，造成父节点下子节点计算出问题
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int child = deque.poll();
                if (child <= 0) {
                    continue;
                }
                int p = parents[child];
                cnt[p] += cnt[child];
                if (visited[p]) {
                    deque.add(p);
                    visited[p] = false;
                }
            }
        }

        int Max = n - 1;
        int Count = 0;
        for (int i = 0; i < n; i++) {
            int tmp = n - 1;
            int res = 1;
            for (int c : map.getOrDefault(i, new ArrayList<>())) {
                res *= cnt[c];
                tmp -= cnt[c];
            }
            res *= tmp == 0 ? 1 : tmp;
            if (res == Max) {
                Count++;
            }
            if (res > Max) {
                Max = res;
                Count = 1;
            }
        }

        return Count;
    }

    long maxScore = 0;
    int cnt = 0;
    int n;
    List<Integer>[] children;

    // 根据数据关系，转化为父节点与子节点关系，进而使用深度优先搜索
    // TODO 只需要有父子节点关系，无须建树，也可以深度优先遍历
    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        // 记录每个节点的子节点关系
        children = new List[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int p = parents[i];
            if (p != -1) {
                children[p].add(i);
            }
        }

        dfs(0);
        return cnt;
    }

    private int dfs(int node) {
        // 初始得分
        long score = 1;
        int size = n - 1;
        // 计算各个子树的分数
        for (int c : children[node]) {
            // 递归计算子节点数量
            int t = dfs(c);
            score *= t;
            size -= t;
        }

        // 顶部子树的分数
        if (node != 0) {
            score *= size;
        }

        if (score == maxScore) {
            cnt++;
        } else if (score > maxScore) {
            maxScore = score;
            cnt = 1;
        }

        // 返回值：当前节点为根节点的子树的节点数量
        return n - size;
    }


    public static void main(String[] args) {
        Q16 q = new Q16();
        int[] nums = new int[]{
//                -1, 2, 0
//                , 2, 0
                -1, 9, 3, 0, 13, 2, 10, 2, 18, 16, 18, 5, 9, 3, 4, 13, 0, 12, 16
        };
        q.countHighestScoreNodes2(nums);
    }
}
