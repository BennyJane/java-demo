package benny.jane.com.leetcode.dp.simple;

import java.util.ArrayList;
import java.util.List;

public class Q5_1 {
    int res = 0;
    public int countNumbersWithUniqueDigits(int n) {
        List<Integer> list = new ArrayList<>();
        backtrack(n, 0, list);
        return res;
    }

    public void backtrack(int n, int index, List<Integer> list) {
        if (index == n) {
            res++;
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (list.contains(i)) continue;
            if (list.size() == 0 && i == 0) {
                backtrack(n, index + 1, list);
            } else {
                list.add(i);
                backtrack(n, index + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Q5_1 q = new Q5_1();
        q.countNumbersWithUniqueDigits(3);
    }
}


