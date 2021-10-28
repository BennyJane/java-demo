package benny.jane.com.reflectDemo;

public class ReflectParent {
    // 属性
    private String privateAttr;
    public String publicAttr;

    // 构造方法
    public ReflectParent(String attr1) {
        this.publicAttr = attr1;
    }

    private ReflectParent() {
    }

    // 方法

    public void funcExc() {
        System.out.println("ReflectParent of funcExc");
    }

    private void funcExc1() {
        System.out.println("ReflectParent of funcExc1");
    }
}
