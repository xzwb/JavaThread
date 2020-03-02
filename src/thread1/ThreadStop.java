package thread1;

/**
 * 判断线程停止的两个方法
 */
public class ThreadStop extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5000000; i++) {
//             System.out.println(i);
        }
    }
}

class Test3 {
    public static void main(String[] args) {
        try {
            ThreadStop stop = new ThreadStop();
            stop.start();
            Thread.sleep(1);
//            Thread.currentThread().interrupt();
            stop.interrupt();
//            System.out.println("是否停止? " + Thread.interrupted());
            System.out.println("是否停止? " + stop.isInterrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
