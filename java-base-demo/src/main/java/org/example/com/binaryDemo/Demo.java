package org.example.com.binaryDemo;

public class Demo {
    public static void main(String[] args) {
        method();
        method2();
        method3();
    }

    /**
     * 异或、与 计算余数，除数必须是2的n次方
     */
    public static void method() {
        System.out.println("[计算余数]： ");
        // 计算除以32的余数
        System.out.println(48 % 36);
        // 位异或 ^
        System.out.println(48 ^ 32);
        // FIXME & 位与符号需要减一
        System.out.println(48 & 31);


        // ERROR: 错误操作
        System.out.println(48 % 40);
        System.out.println(48 ^ 40);
    }

    /**
     * 计算与2的整数幂之间的除法, 乘法转换
     */
    public static void method2() {
        System.out.println("[乘除法 转换]： ");
        System.out.println(32 / 32);
        System.out.println(32 >> 5);
        System.out.println(1 * 32);
        System.out.println(1 << 5);
    }

    public static void method3() {
        System.out.println("[交换两个值]： ");
        int a = 10, b = 20;
        System.out.println(a + " --- " + b);

        // NO.1
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println(a + " --- " + b);
        // NO.1
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a + " --- " + b);
    }
}
