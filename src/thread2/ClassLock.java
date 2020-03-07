package thread2;

/**
 * class锁和对象所
 */
public class ClassLock {
    synchronized public static void method() {
        System.out.println("Method getlock time = " + System.currentTimeMillis() +
                "name = " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method setlock time = " + System.currentTimeMillis() +
                "name = " + Thread.currentThread().getName());
    }

    synchronized public static void method1() {
        System.out.println("Method getlock time = " + System.currentTimeMillis() +
                "name = " + Thread.currentThread().getName());
        System.out.println("Method setlock time = " + System.currentTimeMillis() +
                "name = " + Thread.currentThread().getName());
    }

    synchronized public void method2() {
        System.out.println("Method getlock time = " + System.currentTimeMillis() +
                "name = " + Thread.currentThread().getName());
        System.out.println("Method setlock time = " + System.currentTimeMillis() +
                "name = " + Thread.currentThread().getName());
    }
}

class ClassThread1 extends Thread {
    private ClassLock classLock;

    public ClassThread1(ClassLock classLock) {
        this.classLock = classLock;
    }

    @Override
    public void run() {
        classLock.method();
    }
}

class ClassThread2 extends Thread {
    private ClassLock classLock;

    public ClassThread2(ClassLock classLock) {
        this.classLock = classLock;
    }

    @Override
    public void run() {
        classLock.method1();
    }
}

class ClassThread3 extends Thread {
    private ClassLock classLock;

    public ClassThread3(ClassLock classLock) {
        this.classLock = classLock;
    }

    @Override
    public void run() {
        classLock.method2();
    }
}

class ClassLockMain {
    public static void main(String[] args) {
        ClassLock classLock = new ClassLock();
        ClassThread1 thread1 = new ClassThread1(classLock);
        thread1.setName("a");
        ClassThread2 thread2 = new ClassThread2(classLock);
        thread2.setName("b");
        ClassThread3 thread3 = new ClassThread3(classLock);
        thread3.setName("c");
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}