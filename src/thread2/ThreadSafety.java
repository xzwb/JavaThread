package thread2;

/**
 * 方法内部的私有变量线程安全
 */
public class ThreadSafety {
    public void add(String name) {
        int num = 0;
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

class ThreadSafetyA extends Thread {
    private ThreadSafety threadSafety;

    public ThreadSafetyA(ThreadSafety threadSafety) {
        super();
        this.threadSafety = threadSafety;
    }

    @Override
    public void run() {
        super.run();
        threadSafety.add("a");
    }
}

class ThreadSafetyB extends Thread {
    private ThreadSafety threadSafety;

    public ThreadSafetyB(ThreadSafety threadSafety) {
        super();
        this.threadSafety = threadSafety;
    }

    @Override
    public void run() {
        super.run();
        threadSafety.add("b");
    }
}

class ThreadSafetyTest {
    public static void main(String[] args) {
        ThreadSafety threadSafety = new ThreadSafety();
        ThreadSafetyB threadSafetyB = new ThreadSafetyB(threadSafety);
        ThreadSafetyA threadSafetyA = new ThreadSafetyA(threadSafety);
        threadSafetyA.start();
        threadSafetyB.start();
    }
}



