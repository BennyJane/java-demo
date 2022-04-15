package org.example.com.innerClassDemo;

/**
 * 匿名内部类
 */
public class AnonymousClass {

    public static void main(String[] args) {
        AnonymousClass out = new AnonymousClass();
        String name = "China";
        String city = "Shanghai";
        Inner in = out.getInner(name, city);
        in.getName();
    }

    public Inner getInner(String n, final String c) {
        // FIXME 修改n的值后，匿名内部类将无法使用n变量，
        //  报错：从内部类引用的本地变量必须是最终变量或实际上的最终变量
        // n = "ASU";
        final String finalVal = "final local val";
        String val = "local val";
        String val1 = n;

        Inner in = new Inner() {

            private String country = n;
            private String city = c;

            private String outerFinalLocalVal = finalVal;
            private String outerLocalVal = val;
            private String outerLocalValName = val1;

            {
                String c = "这是一个新的变量";
                // n = "不允许重新赋值";
            }

            @Override
            String getName() {
                System.out.println("inner cls,n: " + n);
                System.out.println("inner cls.c: " + c);
                System.out.println("inner cls.country " + country);
                System.out.println("outerFinalLocalVal " + finalVal);
                System.out.println("outerLocalVal " + val);
                System.out.println("outerLocalValName " + val1);
                return null;
            }
        };

        System.out.println("params: " + n);

        return in;
    }
}


abstract class Inner {

    private String outName;

    public Inner() {
    }

    Inner(String name, String city) {
        System.out.println(name + ": " + city);
        this.outName = name;
    }

    abstract String getName();
}