package thread1;

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
