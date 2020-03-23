package thread6;

/**
 * 懒汉模式与多线程
 */
public class MyObject {
    private static MyObject object = null;

    private MyObject() {
    }

    public static MyObject getInstance() {
        if (object == null) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            object = new MyObject();
            synchronized (MyObject.class) {
                if (object == null) {
                    object = new MyObject();
                }
            }
        }
        return object;
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(MyObject.getInstance().hashCode());
    }
}

class Test {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        thread.start();
        thread1.start();
        thread2.start();
    }
}