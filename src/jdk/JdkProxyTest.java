package jdk;

public class JdkProxyTest {
    public static void main(String[] args) {
        // 创建代理对象
        JdkProxyEx jdkProxyEx = new JdkProxyEx();
        Hello proxy = (Hello) jdkProxyEx.bind(new HelloImpl());
        proxy.sayHello();
    }
}
