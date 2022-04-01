package org.example.com.base;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理
 */
public class StringDeal {
    public static void main(String[] args) {
        method1();
    }

    public static void method1() {
        String strDemo = "Abide";
        int length = strDemo.length();  // 是方法，不是属性（数组）
        char[] array = strDemo.toCharArray();
        int arrayLen = array.length;


        String upperStr = strDemo.toUpperCase(Locale.ROOT);
        System.out.println(String.format("toUpperCase: %s -> %s", strDemo, upperStr));
        String lowerStr = strDemo.toLowerCase();
        System.out.println(String.format("toLowerCase: %s -> %s", strDemo, lowerStr));

        char firstChar = strDemo.charAt(0);

        boolean isContainBI = strDemo.contains("bi");
        boolean isContainZ = strDemo.contains("z");
        System.out.println("contains: " + isContainBI + " , " + isContainZ);

        boolean isEndWithDE = strDemo.endsWith("de");
        boolean isStartWithDE = strDemo.startsWith("de");
        System.out.println("endWith: " + isEndWithDE + "startWith: " + isStartWithDE);
        int index1 = strDemo.indexOf('A');
        System.out.println(index1);
        int index2 = strDemo.indexOf('z');
        System.out.println(index2);
        // 该字符最后出现的索引
        int index3 = strDemo.lastIndexOf("e");
        System.out.println(index3);

        // char int 转换
        int aASCIIVal = 'a';
        int aIndex = 'a' - 'a';
        System.out.println("a ASCII: " + aASCIIVal + "; aIndex: " + aIndex);

        //
        String[] words = strDemo.split("b");
        // 数字 转 字符串
        String ten = String.valueOf(10);

        //
        String subStr = strDemo.substring(0, 5);
        System.out.println(subStr);

        // 正则匹配
        boolean isMatch = strDemo.matches("[ADWS]\\d{1}\\d?");

        Pattern p = Pattern.compile("[a-z]]");
        Matcher m = p.matcher(strDemo);
        if (m.find()) {
            System.out.println("find is true");
        }

    }


}
