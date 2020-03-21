package thread4;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class ReadLock {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void read() {
        lock.readLock().lock();
        System.out.println("获取读锁" + Thread.currentThread().getName() +
                " " + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.readLock().unlock();
    }
}

class ThreadA1 extends Thread {
    private ReadLock lock;

    public ThreadA1(ReadLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.read();
    }
}

class ThreadB1 extends Thread {
    private ReadLock lock;

    public ThreadB1(ReadLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.read();
    }
}


class Test1 {
    public static void main(String[] args) {
        ReadLock lock = new ReadLock();
        ThreadA1 threadA1 = new ThreadA1(lock);
        threadA1.start();
        ThreadB1 threadB1 = new ThreadB1(lock);
        threadB1.start();
    }
}