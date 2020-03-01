package thread1;

/**
 * 使用继承Thread的方式实现线程
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread : " + Thread.currentThread().getName());
    }
}

class MyThreadTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("main : " + Thread.currentThread().getName());
    }
}
