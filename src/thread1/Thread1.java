package thread1;

/**
 * 构造方法是谁调用的
 */
public class Thread1 extends Thread {
    public Thread1() {
        System.out.println(this.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println(this.currentThread().getName());
    }
}


class Test2 {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.start();
//        thread1.run();
    }
}
