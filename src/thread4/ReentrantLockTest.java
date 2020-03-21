package thread4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock对象测试
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThread thread = new MyThread(myService);
        MyThread thread1 = new MyThread(myService);
        MyThread thread2 = new MyThread(myService);
        MyThread thread3 = new MyThread(myService);
        MyThread thread4 = new MyThread(myService);
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

class MyService {
    private Lock lock = new ReentrantLock();

    public void method() {
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("THreadName = " + Thread.currentThread().getName() + ("" + (i + 1)));
        }
        lock.unlock();
    }
}

class MyThread extends Thread {
    private MyService myService;

    public MyThread(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.method();
    }
}

