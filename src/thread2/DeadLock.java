package thread2;

/**
 * 死锁
 */
public class DeadLock extends Thread {
    public String name;
    public Object object = new Object();
    public Object object1 = new Object();


    public void setName1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if (name.equals("a")) {
            synchronized (object) {
                System.out.println("username = " + name);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.out.println("按lock1->lock2执行");
                }
            }
        }
        if (name.equals("b")) {
            synchronized (object1) {
                System.out.println("username = " + name);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object) {
                    System.out.println("按lock2-》lock1执行");
                }
            }
        }
    }
}


class DeadTest {
    public static void main(String[] args) {
        DeadLock t1 = new DeadLock();
        t1.setName1("a");
        Thread thread = new Thread(t1);
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.setName1("b");
        Thread thread1 = new Thread(t1);
        thread1.start();
    }
}