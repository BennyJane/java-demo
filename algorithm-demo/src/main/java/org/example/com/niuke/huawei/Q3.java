package org.example.com.niuke.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//HJ17 坐标移动
public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] word = input.split(";");
        int[] start = new int[]{0, 0};
        for (int i = 0; i < word.length; i++) {
            String cur = word[i];
            if (cur.length() > 3 || cur.length() <= 1) {
                continue;
            }
            char first = cur.charAt(0);
            String sub = cur.substring(1, cur.length());
            // 首字母必须为ADWS，且不能为数字或其他
            if (first == 'A') {
                // 剩余字符串必须可以解析为数字
                start[0] -= parseNum(sub);
            } else if (first == 'D') {
                start[0] += parseNum(sub);
            } else if (first == 'W') {
                start[1] += parseNum(sub);
            } else if (first == 'S') {
                start[1] -= parseNum(sub);
            } else {
                continue;
            }
        }

        System.out.println(start[0] + "," + start[1]);
        sc.close();
    }

    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            int x = 0, y = 0;
            String[] sArray = s.split(";");
            String res = "[ADWS]\\d{1}\\d?";
            for (int i = 0; i < sArray.length; i++) {
                if (sArray[i].matches(res)){
                    String cur = sArray[i];
                    char first = sArray[i].charAt(0);
                    int num = Integer.parseInt(cur.substring(1, cur.length()));
                    map.put(sArray[i].charAt(0), map.getOrDefault(sArray[i].charAt(0), 0) + Integer.valueOf(sArray[i].substring(1)));
                }
            }
            x = x - map.get('A') + map.get('D');
            y = y - map.get('S') + map.get('W');
            System.out.println(x + "," + y);
            map.clear();
        }
        scanner.close();
    }

    private static int parseNum(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return 0;
            }
        }
        return Integer.parseInt(s);
    }
}
