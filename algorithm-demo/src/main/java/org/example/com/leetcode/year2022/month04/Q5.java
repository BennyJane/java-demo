package org.example.com.leetcode.year2022.month04;

import java.util.HashMap;
import java.util.Map;

//762. 二进制表示中质数个计算置位
public class Q5 {

    private static Map<Integer, Boolean> map = new HashMap<>();
    private static Map<Integer, Integer> memo = new HashMap<>();

    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            int cnt = countOne(i);
            if (cnt > 1 && check(cnt)) {
                ans++;
            }
        }

        return ans;
    }

    private int countOne(int num) {
        if (memo.containsKey(num)) {
            return memo.get(num);
        }
        int cnt = 0;
        for (char c : Integer.toBinaryString(num).toCharArray()) {
            if (c == '1') {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean check(int num) {
        if (map.containsKey(num)) {
            return map.get(num);
        }

        boolean res = true;
        if (num < 2) {
            map.put(num, res);
            return res;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                res = false;
                break;
            }
        }

        map.put(num, res);
        return res;
    }

    public static void main(String[] args) {
        Q5 q = new Q5();
        q.countPrimeSetBits(10, 15);
    }
}
