package org.example.com.common;

public class Q1 {

    /**
     * 给定一半数字作为回文字符串的一半，获取回文数字 p
     * 12 -> 1221
     */
    private static void method1() {
        // 只构造长度为偶数的回文串
        for (int left = 9; left > 0; left--) {
            // 左侧数值为left
            int p = left, x = left;
            while (x > 0) {
                // p向左侧移动，x计算最右侧单个数字
                p = p * 10 + x % 10;
                x /= 10;
            }
            System.out.println(p);
        }

        // 使用for循环
        int left = 10;
        long p = left;
        for (int x = left; x > 0; x /= 10) {
            // 翻转左半部分到其自身末尾，构造回文数 p
            p = p * 10 + x % 10;
        }
        System.out.println(p);

        // 使用数组存储，然后再转字符串，转数值
        StringBuilder sb = new StringBuilder(String.valueOf(left));
        String s = String.valueOf(left);
        for (char c : s.toCharArray()) {

        }
    }

    /**
     * 遍历给定数值的所有因子
     * 判断是否为质数
     */
    private static void method2() {
        int target = 1523;

        boolean flag = true;
        int start = 2;
        // 只遍历start <= 另一个因数的情况
        while (start * start <= target) {
            if (target % start == 0) {
                System.out.println("存在因数： " + start + ", " + target / start);
                // target 不是质数，存在非1、target的因数
                flag = false;
            }
            start++;
        }
        if (flag) {
            System.out.println(target + " 是质数.");
        }

    }

    public static void main(String[] args) {
//        method1();

        method2();
    }
}
