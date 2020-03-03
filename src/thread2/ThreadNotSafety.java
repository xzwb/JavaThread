package thread2;

/**
 * 实例变量非线程安全
 * add()方法上加锁后安全
 */
public class ThreadNotSafety {
    private int num = 0;
    synchronized public void add(String name) {
        if (name.equals("a")) {
            num = 100;
            System.out.println("a set over !!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            num = 200;
            System.out.println("b set over !!");
        }
        System.out.println(name + " : num = " + num);
    }
}

class ThreadNotSafetyA extends Thread {
    private ThreadNotSafety threadSafety;

    public ThreadNotSafetyA(ThreadNotSafety threadSafety) {
        super();
        this.threadSafety = threadSafety;
    }

    @Override
    public void run() {
        super.run();
        threadSafety.add("a");
    }
}
class ThreadNotSafetyB extends Thread {
    private ThreadNotSafety threadSafety;

    public ThreadNotSafetyB(ThreadNotSafety threadSafety) {
        super();
        this.threadSafety = threadSafety;
    }

    @Override
    public void run() {
        super.run();
        threadSafety.add("b");
    }
}

class ThreadNotSafetyTest {
    public static void main(String[] args) {
        ThreadNotSafety threadSafety = new ThreadNotSafety();
        ThreadNotSafetyB threadSafetyB = new ThreadNotSafetyB(threadSafety);
        ThreadNotSafetyA threadSafetyA = new ThreadNotSafetyA(threadSafety);
        threadSafetyA.start();
        threadSafetyB.start();
    }
}
