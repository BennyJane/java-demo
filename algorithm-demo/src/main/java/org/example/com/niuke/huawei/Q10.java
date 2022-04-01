package org.example.com.niuke.huawei;

import java.util.Scanner;

//HJ36 字符串加密
public class Q10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String key = sc.nextLine();
            String s = sc.nextLine();
            char[] dict = new char[26];

            boolean[] visited = new boolean[26];
            int start = 0;
            for (char c : key.toCharArray()) {
                int index = c - 'a';
                if (!visited[index]) {
                    visited[index] = true;
                    dict[start] = c;
                    start++;
                }
            }

            for (int i = 0; i < 26; i++) {
                if (!visited[i]) {
                    dict[start] = (char) ('a' + i);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                int index = c - 'a';
                sb.append(dict[index]);
            }

            System.out.println(sb.toString());
        }
    }
}
