package thread2;

/**
 * volatile的线程不安全
 */
public class MyVolatile extends Thread {
    /*volatile*/ public static int count;

    synchronized private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("count = " + count);
    }

    @Override
    public void run() {
        addCount();
    }
}

class VolatileTest {
    public static void main(String[] args) {
        MyVolatile[] myVolatiles = new MyVolatile[100];
        for (int i = 0; i < 100; i++) {
            myVolatiles[i] = new MyVolatile();

        }
        for (int i = 0; i < 100; i++) {
            myVolatiles[i].start();
        }
    }
}