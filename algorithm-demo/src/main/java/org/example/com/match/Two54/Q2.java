package org.example.com.match.Two54;

import java.util.Arrays;

public class Q2 {
    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        int index = 0;
        while (true) {
            if (index > n - 1) {
                index = 0;
            }
            int cost = chalk[index];
            if (cost > k) {
                break;
            } else {
                k -= cost;
            }
            index++;
        }

        return index;
    }

    public int chalkReplacer2(int[] chalk, int k) {
        int ans = 0;

        int res = k;
        int sum = 0;
        for (int i = 0; i < chalk.length; i++) {
            sum += chalk[i];
            if (sum > res) {
                ans = i;
                break;
            }
        }
        // chalk的总和小于k
        res = k % sum;
        int sum2 = 0;
        for (int i = 0; i < chalk.length; i++) {
            sum2 += chalk[i];
            if (sum2 > res) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        long n1 = 10000;
        int res = (int) (10 % n1);
        System.out.println(res);

        System.out.println(539095482 % 36566389);
    }


}

/*
if (k > sum && sum > 0) {
    res = (int) (k % sum);
} else {
    res = k;
}
*/
