package org.example.com.niuke.huawei;

import java.util.*;

public class Q15 {
}


class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            List<String> array = centerToBack(line);
            Deque<Integer> sk = new ArrayDeque<>();

            for (String s : array) {
                if ("+".equals(s)) {
                    Integer a = sk.pop();
                    Integer b = sk.pop();
                    int tmp = a + b;
                    sk.push(tmp);
                } else if ("-".equals(s)) {
                    Integer a = sk.pop();
                    Integer b = sk.pop();
                    int tmp = b - a;
                    sk.push(tmp);
                } else if ("*".equals(s)) {
                    Integer a = sk.pop();
                    Integer b = sk.pop();
                    int tmp = a * b;
                    sk.push(tmp);
                } else if ("/".equals(s)) {
                    Integer a = sk.pop();
                    Integer b = sk.pop();
                    int tmp = b / a;
                    sk.push(tmp);
                } else {
                    sk.push(Integer.parseInt(s));
                }
            }

            Integer result = sk.pop();
            System.out.println(result);
        }

        sc.close();
    }

    public static List<String> centerToBack(String s) {
        // 处理原表达式，分离数值、括号
        List<String> center = stringToList(s);

        // 数组：最后的后缀序列
        List<String> backList = new ArrayList<>();
        // 栈：记录运算符号，并处理优先级、括号
        Deque<String> sk = new ArrayDeque<>();


        for (String c : center) {
            if (c.equals("(")) {
                sk.push(c);
            } else if (c.equals("*") || c.equals("/")) {
                sk.push(c);
            } else if (c.equals("+") || c.equals("-")) {
                // ！！优先处理乘除法
                while (!sk.isEmpty() && "*/+-".contains(sk.peek())) {
                    backList.add(sk.pop());
                }
                sk.push(c);
            } else if (c.equals(")")) {
                // 需要将栈中对应左括号前字符，全部弹出栈
                while (!"(".equals(sk.peek())) {
                    backList.add(sk.pop());
                }
                // 弹出左括号
                sk.pop();
            } else {
                backList.add(c);
            }
        }

        // !! 将栈中操作符号全部压入res中
        while (!sk.isEmpty()) {
            backList.add(sk.pop());
        }

        return backList;
    }


    // 表达式转列表：分割正数、运算符号
    public static List<String> stringToList(String expression) {
        // 特殊处理：将其他括号转为()，统一处理
        expression = expression.replace("{", "(");
        expression = expression.replace("}", ")");
        expression = expression.replace("[", "(");
        expression = expression.replace("]", ")");

        int n = expression.length();
        List<String> array = new ArrayList<>();

        StringBuilder num = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                num.append(c);
                if (i == n - 1 || !Character.isDigit(expression.charAt(i+1))) {
                    array.add(num.toString());
                    num = new StringBuilder();
                }
            } else if (c == '-') {
                // !! 处理负数的情况: 位于首位、括号后
                if (i == 0 || expression.charAt(i - 1) == '(') {
                    num.append(c);
                } else {
                    // 先保存数值，再添加 减号 运算符
                    array.add(c + "");
                }
            } else {
                array.add(String.valueOf(c));
            }
        }

        return array;
    }
}