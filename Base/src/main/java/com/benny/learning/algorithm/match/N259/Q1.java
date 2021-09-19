package com.benny.learning.algorithm.match.N259;

public class Q1 {
    public int finalValueAfterOperations(String[] operations) {
        int ans =0 ;
        for (String s : operations) {
            if (s.contains("+")) {
                ans++;
            } else {
                ans--;
            }
        }
        return ans;
    }
}
