package org.example.com.baseDemo;

public class IntDemo {
    public static void main(String[] args) {
        funcExc1();
    }

    static void funcExc() {
        Integer n = new Integer(10);
        Long l = new Long(10L);
        Double d = new Double(1L);
        Float f = new Float(1L);
        Short s = new Short("1");

        // FIXME 默认视作二进制， 打印输出十进制：41637
        System.out.println(00121245);
    }

    static void funcExc1() {
        int num1 = 10;
        Integer num2 = new Integer(20);
        String s = num2.toString();
        System.out.println(s);
        // 进制转换方法
        System.out.println(Integer.toString(20, 2));
        System.out.println(Integer.toString(10, 16));


        String res = System.getProperty("name");
    }
}
