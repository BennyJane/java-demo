package com.benny.jane.plugins;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.Executor;

@Component
@Intercepts({
        @Signature(type = Executor.class, method = "query",
                args = {
                        MappedStatement.class,
                        Object.class,
                        RowBounds.class,
                        ResultHandler.class})})
public class QueryPlugin implements Interceptor {

    // 实际执行方法
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("对Executor.query方法进行了增强");
        System.out.println("before Mybatis plugin....");
        //执行原方法
        Object obj = invocation.proceed();

        System.out.println("after Mybatis plugin....");
        return obj;
    }

    // 生成一个拦截器对象，丢到拦截器链中
    @Override
    public Object plugin(Object target) {
        System.out.println("将要包装的目标对象" + target);
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("配置的初始化参数" + properties);
    }
}
