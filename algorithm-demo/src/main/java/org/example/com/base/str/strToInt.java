package org.example.com.base.str;

/**
 * 数字字符串，如何解析为 数值类型
 * 125,1551,-1515,541
 * 考虑正负情况
 */
public class strToInt {

    public static void main(String[] args) {
        System.out.println(parse1("-12122"));
        System.out.println(parse1("12122"));
    }

    private static int parse1(String s) {
        int num = 0;
        // 记录是否负数，并修改起点
        int index = 0;
        boolean negative = false;
        if (s.charAt(0) == '-') {
            negative = true;
            index++;
        }
        for (int i = index; i < s.length(); i++) {
            char c = s.charAt(i);
            // FIXME 左侧高位，需要左侧一位
            num = num * 10 + c - '0';
        }
        // FIXME 添加负号
        return negative ? -1 * num : num;
    }
}
