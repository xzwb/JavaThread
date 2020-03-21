package thread4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition对象
 */
public class UserCondition {
    public static void main(String[] args) {
        MyService1 myService1 = new MyService1();
        ThreadA threadA = new ThreadA(myService1);
        threadA.start();
    }
}

class MyService1 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void method() {
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class ThreadA extends Thread {
    private MyService1 myService1;

    public ThreadA(MyService1 myService1) {
        this.myService1 = myService1;
    }

    @Override
    public void run() {
        myService1.method();
    }
}


