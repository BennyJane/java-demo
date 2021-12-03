package org.exmaple.com.match.No243;


public class Q1 {
    public static void main(String[] args) {
        Q1 q = new Q1();
        q.isSumEqual("j", "j", "bi");
    }

    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int one = cal(firstWord);
        int two = cal(secondWord);
        int three = cal(targetWord);

        int sum = one + two;
        return sum == three ? true : false;
    }

    private static int cal(String s) {
        int num = 0;
        for (char c : s.toCharArray()) {
            int n = c - 'a';
            // TODO 这里是加号，不是累加
            num = num * 10 + n;
        }

        return num;
    }
}


