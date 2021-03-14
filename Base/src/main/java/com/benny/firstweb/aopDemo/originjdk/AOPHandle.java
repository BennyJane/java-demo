package com.benny.firstweb.aopDemo.originjdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 切口类必须实现 InvocationHandler接口， invoke方法
 * 接口代理： 这个类继承这个接口，并且代理要切面的类
 */
public class AOPHandle implements InvocationHandler {
    // 存储代理对象
    private Object obj;

    public AOPHandle(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置代理");
        // 反射调用
        Object ret = method.invoke(obj, args);
        System.out.println("后置代理");
        // 返回反射调用方法的返回值
        return ret;
    }
}
