package org.example.com.niuke.huawei;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

//HJ26 字符串排序
public class Q5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();

            // 先提取英文字母: 按照索引升序
            List<Character> letters = new ArrayList<>();
            for (char c : s.toCharArray()) {
                if (Character.isLetter(c)) {
                    letters.add(c);
                }
            }

            //
            // 排序：按照A-Z升序排列 TODO 相同字母，保留原来的顺序
            letters.sort(new Comparator<Character>() {
                public int compare(Character c1, Character c2) {
                    return Character.toLowerCase(c1) - Character.toLowerCase(c2);
                }
            });

            // 排序方式
            letters.sort(Comparator.comparingInt(Character::toLowerCase));

            StringBuilder sb = new StringBuilder();
            int start = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isLetter(c)) {
                    sb.append(letters.get(start));
                    start++;
                } else {
                    sb.append(c);
                }
            }

            System.out.println(sb.toString());
        }
    }
}
