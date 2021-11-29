package org.exmaple.com.theory;

public class Demo {
    static class Foo {
        String name;
        Integer age;

        public Foo() {
        }

        public Foo(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Foo{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    final Foo foo = new Foo();
    final Integer cost = 100;

    public void setFoo(String name) {
        foo.name = name;
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        System.out.println(d.cost);
        System.out.println(d.foo);
        d.setFoo("benny");
        System.out.println(d.foo);
        // 无法直接修改d.cost属性
        // d.cost = 200;

        // 不能重新赋值
        // d.foo = new Foo("jane");
    }


}
