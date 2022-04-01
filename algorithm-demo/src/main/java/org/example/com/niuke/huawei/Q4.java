package org.example.com.niuke.huawei;


import java.util.Arrays;
import java.util.Scanner;

//HJ20 密码验证合格程序
public class Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.length() <= 8 || input.contains(" ") || input.contains("\\n")) {
                System.out.println("NG");
                continue;
            }
            // ============================================================================


            int[] cnt = new int[4];
            for (char c : input.toCharArray()) {
                if (Character.isDigit(c)) {
                    cnt[0] = 1;
                } else if (Character.isUpperCase(c)) {
                    cnt[1] = 1;
                } else if (Character.isLowerCase(c)) {
                    cnt[2] = 1;
                } else {
                    cnt[3] = 1;
                }
            }
            if (Arrays.stream(cnt).sum() < 3) {
                System.out.println("NG");
                continue;
            }



            // ============================================================================

            // TODO 是否存在重复字符串
            int n = input.length();
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                for (int j = i + 2; j <= n; j++) {
                    String tmp = input.substring(i, j);
                    String tailStr = input.substring(j, n);
                    if (tmp.length() > 2 && tailStr.contains(tmp)) {
                        System.out.println("NG");
                        flag = false;
                        break;
                    }
                }
                // 嵌套循环，需要多次break才能退出
                if (!flag) {
                    break;
                }
            }

            // FIXME 只需要一层循环
            // 只需要判断是否存在最短满足退出条件即，存在长度为3的重复子字符串
            for (int i = 0; i < n - 5; i++) {
                String pre = input.substring(i, i + 3);
                String tailStr = input.substring(i + 3);
                if (tailStr.contains(pre)) {
                    flag = false;
                    System.out.println("NG");
                    break;
                }
            }

            // 桥套循环，逐个判定
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (input.charAt(i) == input.charAt(j)
                            && input.charAt(i + 1) == input.charAt(j + 1)
                            && input.charAt(i + 2) == input.charAt(j + 2)
                    ) {
                        flag = false;
                        System.out.println("NG");
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }


            if (flag) {
                System.out.println("OK");
            }
        }
    }

    // 正则表达式
    public static void main2(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String pwd = in.nextLine();
            // 长度check, 相同长度超2的子串重复check
            if (pwd.length() <= 8 || pwd.replaceAll("(.{3,})(?=.{3,}\\1)", "").length() < pwd.length()) {
                System.out.println("NG");
                continue;
            }
            // 大小写字母.数字.其它符号check
            int count = 0;
            if (pwd.matches(".*\\d+.*")) count++;
            if (pwd.matches(".*[a-z]+.*")) count++;
            if (pwd.matches(".*[A-Z]+.*")) count++;
            if (pwd.matches(".*[\\p{P}\\p{S}]+.*")) count++;
            if (count < 3) {
                System.out.println("NG");
                continue;
            }
            System.out.println("OK");
        }
        in.close();
    }
}
