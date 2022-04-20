package org.example.com.high.interview.base;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculate {
    public int calculate(String s) {
        Deque<Integer> deque = new ArrayDeque<>();
        Deque<Character> sign = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (c == '+' || c == '-' || c == '/' || c == '*') {
                sign.push(c);
            } else {
                sb.append(c);
                if (i +1 >= n || !Character.isDigit(s.charAt(i +1))) {
                    String num = sb.toString();
                    int cur = Integer.parseInt(num);

                    if (!sign.isEmpty() && (sign.peek() == '/' || sign.peek() == '*')) {
                        int before = deque.pop();
                        char signPeek = sign.pop();
                        int res = signPeek == '/' ? before / cur : before * cur;
                        deque.push(res);
                    } else {
                        deque.push(cur);
                    }
                    sb = new StringBuilder();
                }
            }

        }


        int ans = 0;
        while (sign.size() > 0) {
            char isAdd = sign.pop();
            int a = deque.pop();
            ans = isAdd == '+' ? ans + a : ans - a;
        }
        ans += deque.peek();

        return ans;
    }

    public static void main(String[] args) {
        Calculate c = new Calculate();
        c.calculate("3+2*2");
    }
}
