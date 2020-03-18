package thread3;

/**
 * 当wait方法遇到interrupt
 */
public class WaitAndInterrupt {
    public void method(Object lock) {
        synchronized (lock) {
            System.out.println("begin ");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(".........");
            }
            System.out.println("end");
        }
    }
}

class Thread1 extends Thread {
    private Object lock;

    public Thread1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        WaitAndInterrupt waitAndInterrupt = new WaitAndInterrupt();
        waitAndInterrupt.method(lock);
    }
}

class Test1 {
    public static void main(String[] args) {
        Object lock = new Object();
        Thread1 thread1 = new Thread1(lock);
        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
//        Thread.interrupted();
    }
}