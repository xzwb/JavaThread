package thread1;

/**
 * 使用实现Runnable借口的方式实现线程
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable : " + Thread.currentThread().getName());
    }
}

class MyRunnableTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
        System.out.println("main : " + Thread.currentThread().getName());
    }
}
