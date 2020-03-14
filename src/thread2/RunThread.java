package thread2;

/**
 * 解决异步死循环
 */
public class RunThread extends Thread {
    volatile public boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        System.out.println("进入run了");
        while (isRunning) {

        }
        System.out.println("线程被终止了");
    }
}


class RunThreadTest {
    public static void main(String[] args) {
        RunThread t = new RunThread();
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.setRunning(false);
        System.out.println("已经赋值为false");
    }
}