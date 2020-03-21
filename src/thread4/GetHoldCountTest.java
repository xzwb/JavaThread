package thread4;

import java.util.concurrent.locks.ReentrantLock;

public class GetHoldCountTest {
    public static void main(String[] args) {
        Service1 service1 = new Service1();
        service1.method1();
        service1.method2();
    }
}

class Service1 {
    private ReentrantLock lock = new ReentrantLock();
    public void method1() {
        lock.lock();
        System.out.println("method1 getHoldCount = " + lock.getHoldCount());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        method2();
        lock.unlock();
    }

    public void method2() {
        lock.lock();
        System.out.println("method2 holdCount = " + lock.getHoldCount());
        lock.unlock();
    }
//    private Object object = new Object();
//    public void method1() {
//        synchronized (object) {
//            System.out.println("......");
//            method2();
//        }
//    }
//
//    private void method2() {
//        synchronized (object) {
//            System.out.println("ssssss");
//        }
//    }
}
