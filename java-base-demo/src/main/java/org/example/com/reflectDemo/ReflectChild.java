package org.example.com.reflectDemo;

public class ReflectChild extends ReflectParent {
    private String privateChildAttr;
    public String publicChildAttr;

    public ReflectChild() {
        super("1");
    }

    public ReflectChild(String attr1, String attr2) {
        super(attr1);
        this.publicChildAttr = attr2;
    }

    // 方法
    public void funcExc3() {
        System.out.println("ReflectParent of funcExc3");
    }

    private void funcExc4() {
        System.out.println("ReflectParent of funcExc4");
    }
}
