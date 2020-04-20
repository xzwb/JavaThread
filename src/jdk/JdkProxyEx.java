package jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyEx implements InvocationHandler {
    // 被代理的类
    private Object target = null;

    /**
     * 建立代理对象和被代理对象之间的关系
     * @param target 被代理对象
     * @return 代理对象
     */
    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    /**
     * 代理方法逻辑
     * @param proxy 代理对象
     * @param method 当前调度方法
     * @param args 当前方法参数
     * @return 代理结果返回
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入代理逻辑方法前");
        System.out.println("调度之前的服务");
        Object obj = method.invoke(target, args);
        System.out.println("调用之后的方法");
        return obj;
    }
}
