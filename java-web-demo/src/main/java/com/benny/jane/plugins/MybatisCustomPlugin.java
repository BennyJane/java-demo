package com.benny.jane.plugins;


import com.benny.jane.mapper.UserMapper;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Properties;


@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {UserMapper.class, Object.class}
)})
public class MybatisCustomPlugin implements Interceptor {

    Properties properties = null;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("before Mybatis plugin....");

        Object obj = invocation.proceed();

        System.out.println("after Mybatis plugin....");
        return obj;
    }

    @Override
    public Object plugin(Object target) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
