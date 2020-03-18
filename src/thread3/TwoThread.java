package thread3;

import java.util.ArrayList;
import java.util.List;

/**
 * wait() notify()
 * 等待/通知 机制
 */
public class TwoThread {
    private static List list = new ArrayList();

    public static void add() {
        list.add("yyf");
    }

    public static int size() {
        return list.size();
    }
}

class ThreadA extends Thread {
     // 锁对象
    private Object lock;

    public ThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                TwoThread.add();
                System.out.println("添加了" + (i + 1) + "个元素");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (TwoThread.size() == 5) {
                    lock.notify();
                    System.out.println("已经发出了通知");
                }
            }
        }
    }
}

class ThreadB extends Thread {
    private Object lock;

    public ThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("kaishi ");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("接收到了");
        }
    }
}

class Test {
    public static void main(String[] args) {
        Object lock = new Object();
        ThreadB threadB = new ThreadB(lock);
        threadB.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadA threadA = new ThreadA(lock);
        threadA.start();
    }
}