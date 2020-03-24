package thread7;

public class GroupThread {
    public static void main(String[] args) {
        ThreadB threadB = new ThreadB();
        ThreadC threadC = new ThreadC();
        ThreadGroup group = new ThreadGroup("yyf的线程组");
        Thread thread = new Thread(group, threadB);
        Thread thread1 = new Thread(group, threadC);
        thread.start();
        thread1.start();
        System.out.println("活动的线程数为 : " + group.activeCount());
        System.out.println("线程组的名称为: " + group.getName());
    }
}

class ThreadB extends Thread {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("ThreadName = " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadC extends Thread {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("ThreadName = " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

