package thread2;

/**
 * 一半同步, 一半异步
 */
public class Half {
    public void doLongTime() {
        for (int i = 0; i < 100; i++) {
            System.out.println("不同步: " + Thread.currentThread().getName() + "  i = " + (i + 1));

        }
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println("同步: " + Thread.currentThread().getName() + "  i = " + (i + 1));
            }
        }
    }
}

class HalfThread extends Thread {
    private Half half;

    public HalfThread(Half half) {
        this.half = half;
    }

    @Override
    public void run() {
        half.doLongTime();
    }
}

class HalfThread1 extends Thread {
    private Half half;

    public HalfThread1(Half half) {
        this.half = half;
    }

    @Override
    public void run() {
        half.doLongTime();
    }
}

class HalfTest {
    public static void main(String[] args) {
        Half half = new Half();
        HalfThread thread = new HalfThread(half);
        thread.setName("a");
        HalfThread thread1 = new HalfThread(half);
        thread1.setName("b");
        thread.start();
        thread1.start();
    }
}