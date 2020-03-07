package thread2;

/**
 * 当多个线程同时在执行synchronized(x){}同步代码块时呈现同步效果
 */
public class MyObject {
}

class ObjectService {
        public void method(MyObject object) {
            synchronized (object) {
                System.out.println("method getLock time = " + System.currentTimeMillis() + " run thread name = " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("method getLock time = " + System.currentTimeMillis() + " run thread name = " + Thread.currentThread().getName());
            }
        }
}

class ObjectThread1 extends Thread {
    private ObjectService service;
    private MyObject object;

    public ObjectThread1(ObjectService service, MyObject object) {
        this.service = service;
        this.object = object;
    }

    @Override
    public void run() {
        service.method(object);
    }
}

class ObjectThread2 extends Thread {
    private ObjectService service;
    private MyObject object;

    public ObjectThread2(ObjectService service, MyObject object) {
        this.service = service;
        this.object = object;
    }

    @Override
    public void run() {
        service.method(object);
    }
}

/**
 * 使用相同的对象监视器
 */
class ObjectThreadTest {
    /**
     * 使用相同的对象监视器
     */
    public static void main(String[] args) {
        MyObject o = new MyObject();
        ObjectService service = new ObjectService();
        // 使用不同的对象监视器
        MyObject object = new MyObject();
        ObjectThread1 thread1 = new ObjectThread1(service, o);
        thread1.setName("a");
//        ObjectThread2 thread2 = new ObjectThread2(service, o);
        ObjectThread2 thread2 = new ObjectThread2(service, object);
        thread2.setName("b");
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}