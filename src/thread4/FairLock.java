package thread4;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁与非公平锁
 */
public class FairLock {
    public static void main(String[] args) {
        Service service = new Service(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("*线程 " + Thread.currentThread().getName() + " 运行了");
                service.method();
            }
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}

class Service {
    private ReentrantLock lock;

    public Service(boolean isFair) {
        lock = new ReentrantLock(isFair);
    }

    public void method() {
        try {
            lock.lock();

            System.out.println("ThreadName = " + Thread.currentThread().getName() +
                    " 获得锁定");
        } finally {
            lock.unlock();
        }
    }
}