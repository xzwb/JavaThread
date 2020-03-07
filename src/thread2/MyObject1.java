package thread2;

/**
 * 当其他线程执行x对象中的synchronized同步方法时呈现同步状态
 */
public class MyObject1 {
    synchronized public void method() {
        System.out.println("method setLock time = " + System.currentTimeMillis() + " run thread name = " + Thread.currentThread().getName());
        System.out.println("-----------------------------------------------");
        System.out.println("method getLock time = " + System.currentTimeMillis() + " run thread name = " + Thread.currentThread().getName());
    }
}

class Service1 {
    public void method(MyObject1 object1) {
        synchronized (object1) {
            System.out.println("method setLock time = " + System.currentTimeMillis() + " run thread name = " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method getLock time = " + System.currentTimeMillis() + " run thread name = " + Thread.currentThread().getName());
        }
    }
}

class ServiceThread1 extends Thread {
    private Service1 service1;
    private MyObject1 object1;

    public ServiceThread1(Service1 service1, MyObject1 object1) {
        this.service1 = service1;
        this.object1 = object1;
    }

    @Override
    public void run() {
        service1.method(object1);
    }
}

class ObjectThread3 extends Thread {
    private MyObject1 object1;

    public ObjectThread3(MyObject1 object1) {
        this.object1 = object1;
    }

    @Override
    public void run() {
        object1.method();
    }
}

class ObjectTest1 {
    public static void main(String[] args) {
        MyObject1 myObject1 = new MyObject1();
        Service1 service1 = new Service1();
        ServiceThread1 thread1 = new ServiceThread1(service1, myObject1);
        thread1.setName("a");
        thread1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ObjectThread3 thread3 = new ObjectThread3(myObject1);
        thread3.setName("b");
        thread3.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}