package thread1;

/**
 * 实例变量数据不共享
 */
public class ThreadSafety extends Thread {
    private int i = 5;

    public ThreadSafety(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        while (i > 0) {
            i--;
            System.out.println(this.currentThread().getName() + ": i = " + i);
        }
    }
}


class Test {
    public static void main(String[] args) {
        ThreadSafety threadSafety = new ThreadSafety("aaa");
        ThreadSafety threadSafety1 = new ThreadSafety("bbb");
        ThreadSafety threadSafety2 = new ThreadSafety("ccc");
        threadSafety.start();
        threadSafety1.start();
        threadSafety2.start();
    }
}