package thread2;

/**
 * 当类中的两个方法都用了锁
 * 那么当A拿到了锁,B想要调用另一个方法也需要同步
 */
public class SyThread {
    synchronized public void methodA() {
        System.out.println("methodA start!!!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("methodA end");
    }

    synchronized public void methodB() {
        System.out.println("methodB start!!!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("methodB end");
    }
}

class SyThreadA extends Thread {
    SyThread syThread;

    public SyThreadA(SyThread syThread) {
        super();
        this.syThread = syThread;
    }

    @Override
    public void run() {
        syThread.methodA();
    }
}

class SyThreadB extends Thread {
    SyThread syThread;

    public SyThreadB(SyThread syThread) {
        super();
        this.syThread = syThread;
    }

    @Override
    public void run() {
        // syThread.methodA();
        syThread.methodB();
    }
}

class SyTest {
    public static void main(String[] args) {
        SyThread syThread = new SyThread();
        SyThreadA syThreadA = new SyThreadA(syThread);
        SyThreadB syThreadB = new SyThreadB(syThread);
        syThreadA.start();
        syThreadB.start();
    }
}