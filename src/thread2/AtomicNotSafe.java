package thread2;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicNotSafe {
    public static AtomicLong aLong = new AtomicLong();

    synchronized public void addNum() {
        System.out.println(Thread.currentThread().getName() + " 加了100以后的值是 " + aLong.addAndGet(100));
        aLong.addAndGet(1);
    }
}

class AtomicNotSafeThread extends Thread {
    private AtomicNotSafe atomicNotSafe;

    public AtomicNotSafeThread(AtomicNotSafe atomicNotSafe) {
        this.atomicNotSafe = atomicNotSafe;
    }

    @Override
    public void run() {
        atomicNotSafe.addNum();
    }
}

class AtomicRun {
    public static void main(String[] args) {
        AtomicNotSafe safe = new AtomicNotSafe();
        AtomicNotSafeThread[] thread = new AtomicNotSafeThread[5];
        for (int i = 0; i < 5; i++) {
            thread[i] = new AtomicNotSafeThread(safe);
        }
        for (int i = 0; i < 5; i++) {
            thread[i].start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(safe.aLong.get());
    }
}