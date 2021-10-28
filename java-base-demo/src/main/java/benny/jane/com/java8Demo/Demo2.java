package benny.jane.com.java8Demo;

public class Demo2 implements FunctionInterface{
    @Override
    public void uniqueFunc() {
        System.out.println(this.getClass().getName() + " is running");
    }

    @Override
    public void notRequired() {
        System.out.println("Demo2 not Required");
    }

    public static void main(String[] args) {
        FunctionInterface func = interfaceFactory.create(Demo2::new);
        func.uniqueFunc();
        func.notRequired();
    }
}
