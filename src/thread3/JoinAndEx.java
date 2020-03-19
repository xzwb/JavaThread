package thread3;

/**
 * join方法和异常
 */
public class JoinAndEx {
    public static void main(String[] args) {
        ThreadJoinB b = new ThreadJoinB();
        b.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadJoinC c = new ThreadJoinC(b);
        c.start();
    }
}

class ThreadJoinA extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            String s = new String();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Math.random();
        }
    }
}

class ThreadJoinB extends Thread {
    @Override
    public void run() {
        ThreadJoinA a = new ThreadJoinA();
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("b线程结束end......");
    }
}

class ThreadJoinC extends Thread {
    private ThreadJoinB b;

    public ThreadJoinC(ThreadJoinB b) {
        this.b = b;
    }

    @Override
    public void run() {
        b.interrupt();
    }
}
