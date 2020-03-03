package thread2;

/**
 * 异常自动释放锁
 */
public class ExceptionThread {
    synchronized public void test() {
        System.out.println("exceptionThread ...... start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 5/0;
        System.out.println("exceptionThread ... end");
    }

    synchronized public void test1() {
        System.out.println("exception test1...... start");
    }
}

class EThreadA extends Thread {
    ExceptionThread thread;

    public EThreadA(ExceptionThread thread) {
        super();
        this.thread = thread;
    }

    @Override
    public void run() {
        super.run();
        thread.test();
    }
}

class EThreadB extends Thread {
    ExceptionThread thread;

    public EThreadB(ExceptionThread thread) {
        super();
        this.thread = thread;
    }

    @Override
    public void run() {
        super.run();
        thread.test1();
    }
}

class ETreadTest {
    public static void main(String[] args) {
        ExceptionThread t  = new ExceptionThread();
        EThreadA eThreadA = new EThreadA(t);
        EThreadB eThreadB = new EThreadB(t);
        eThreadA.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        eThreadB.start();
    }
}
