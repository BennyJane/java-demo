package benny.jane.com.fanXingDemo;

public class ClassDemo<T> {
    private T value;

    public ClassDemo(T value) { // 构造器
        this.value = value;
    }

    public ClassDemo() {

    }

    public void setDemo(T value) {  // 入参类型
        this.value = value;
    }

    public T getDemo() {    // 返回值类型
        System.out.println(value);
        return this.value;
    }

    // 静态方法无法使用传入的泛型类
    // 静态方法定义自身的泛型参数
    public static <Q> Q methodEx(Q value) {
        System.out.println(value);
        return value;
    }

    // 泛型方法
    public <E> E methodEx2(E data) {
        System.out.println(data);
        return data;
    }
}


class Test {
    public static void main(String[] args) {
        ClassDemo cl = new ClassDemo<String>("test");
        cl.getDemo();

        ClassDemo cl2 = new ClassDemo<Integer>(10);
        cl2.getDemo();
        ClassDemo.methodEx("method");
    }
}
