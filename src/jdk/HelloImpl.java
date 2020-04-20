package jdk;

/**
 * 被代理的类
 */
public class HelloImpl implements Hello {
    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}
