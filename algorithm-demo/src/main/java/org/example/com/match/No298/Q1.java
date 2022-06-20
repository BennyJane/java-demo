package org.example.com.match.No298;

public class Q1 {
    public static void main(String[] args) {
        Q1 q = new Q1();
        q.greatestLetter("arRAzFif");
    }

    public String greatestLetter(String s) {
        char[] array = s.toCharArray();

        int[] cnt = new int[26];

        for (char c : array) {
            int index = Character.toLowerCase(c) - 'a';
            if (Character.isLowerCase(c)) {
                cnt[index] |= 1;
            } else {
                cnt[index] |= 2;
            }
        }

        String ans = "";

        int n = array.length;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 3) {
                char c = (char) ('A' + i);
                ans = "" + c;
            }
        }

        return ans;
    }
}
