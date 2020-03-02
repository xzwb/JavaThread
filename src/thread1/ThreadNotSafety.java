package thread1;

/**
 * 数据共享
 */
public class ThreadNotSafety extends Thread{
    private int i = 5;

    @Override
    synchronized public void run() {
        i--;
        System.out.println(this.currentThread().getName() + " i = " + i);
    }
}


class Test1 {
    public static void main(String[] args) {
        ThreadNotSafety t = new ThreadNotSafety();
        Thread thread = new Thread(t, "a");
        Thread thread1 = new Thread(t, "b");
        Thread thread2 = new Thread(t, "c");
        Thread thread3 = new Thread(t, "d");
        Thread thread4 = new Thread(t, "e");
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}