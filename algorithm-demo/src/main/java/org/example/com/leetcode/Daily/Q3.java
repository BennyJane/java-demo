package org.example.com.leetcode.Daily;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/word-search-ii/
 * 212. 单词搜索 II
 */
public class Q3 {

    Set<String> all = new HashSet<>();
    List<String> pos = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, "");
            }
        }

        Map<Character, String> map = new HashMap<>();
        for (String s : all) {
            updateMap(map, s);

            StringBuffer sb = new StringBuffer();
            int len = s.length();
            for (int i = len - 1; i >= 0; i--) {
                sb.append(s.charAt(i));
            }
            updateMap(map, sb.toString());
        }

        List<String> ans = new ArrayList<>();
        for (String s: words) {
            String ori = map.getOrDefault(s.charAt(0), "");
            if (ori.contains(s)) {
                ans.add(s);
            }
        }


        return ans;
    }

    private void updateMap(Map<Character, String> map, String s) {
        Character firstC = s.charAt(0);
        String ori = map.getOrDefault(firstC, "");
        ori += "; " + s;
        map.put(firstC, ori);
    }


    private void dfs(char[][] board, int x, int y, String s) {
        int m = board.length;
        int n = board[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || pos.contains(x + "-" + y)) {
            all.add(s);
            return;
        }

        char c = board[x][y];
        pos.add(x + "-" + y);
        dfs(board, x + 1, y, s + c);
        dfs(board, x - 1, y, s + c);
        dfs(board, x, y + 1, s + c);
        dfs(board, x, y - 1, s + c);
        pos.remove(x + "-" + y);
    }

    public static void main(String[] args) {
        Q3 q = new Q3();
        char[][] board = new char[][]{
//                {'o', 'a', 'a', 'n'},
//                {'e', 't', 'a', 'e'},
//                {'i', 'h', 'k', 'r'},
//                {'i', 'f', 'l', 'v'}

                {'a', 'b', 'c'},
                {'a', 'e', 'd'},
                {'a', 'f', 'g'},
        };
        String[] words = new String[]{
//                "oath", "pea", "eat", "rain"
                "abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"

        };
        q.findWords(board, words);
    }


}


