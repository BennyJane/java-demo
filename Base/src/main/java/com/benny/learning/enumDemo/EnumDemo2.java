package com.benny.learning.enumDemo;

// 枚举类与普通类非常相似，也可以定义构造器，方法等
// 注意构造方法的调用位置：在枚举类外部似乎无法调用构造方法
public enum EnumDemo2 {
    PYTHON("main"), // 实例化枚举类自身，调用相应的构造器方法
    JAVA("third"),
    GO("other"),
    SHELL("second");  // 使用分号

    String description;

    //构造方法:不能使用 public
    EnumDemo2(String desc) {
        this.description = desc;
    }

    EnumDemo2() {
    }

    // 普通方法
    public String getDescription() {
        return description;
    }

    public static void main(String[] args) {
        for (EnumDemo2 e : EnumDemo2.values()) {
            System.out.println(e + " " + e.getDescription());
            System.out.println(e + " " + e.getDescription());
            System.out.println("枚举类元素的类型" + e.getClass().getName());

        }
    }

}
