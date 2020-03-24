package thread7;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        ThreadA threadA = new ThreadA();
        System.out.println("main方法中的状态1: " + threadA.getState());
        Thread.sleep(1000);
        threadA.start();
        Thread.sleep(1000);
        System.out.println("main方法中的状态2: " + threadA.getState());
    }
}

class ThreadA extends Thread {
    public ThreadA() {
        System.out.println("构造方法中的状态 : " + Thread.currentThread().getState());
    }

    @Override
    public void run() {
        System.out.println("运行时的状态： " + Thread.currentThread().getState());
    }
}
