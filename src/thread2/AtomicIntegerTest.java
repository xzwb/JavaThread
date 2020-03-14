package thread2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类型
 */
public class AtomicIntegerTest extends Thread {
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(count.incrementAndGet());
        }
    }
}

class AtomicTest {
    public static void main(String[] args) {
        AtomicIntegerTest atomicIntegerTest = new AtomicIntegerTest();
        Thread thread = new Thread(atomicIntegerTest);
        thread.start();
        Thread thread1 = new Thread(atomicIntegerTest);
        thread1.start();
        Thread thread2 = new Thread(atomicIntegerTest);
        thread2.start();
    }
}