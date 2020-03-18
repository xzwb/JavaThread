package thread3;

/**
 * 一生产与一消费模型
 */
public class P {
    private String lock;

    public P(String lock) {
        this.lock = lock;
    }

    public void setValue() {
        synchronized (lock) {
            if (!ValueObject.value.equals("")) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String value = System.currentTimeMillis() + " _ _ _" + System.nanoTime();
            System.out.println("set的值是 " + value);
            ValueObject.value = value;
            lock.notify();
        }
    }
}

class C {
    private String lock;

    public C(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        synchronized (lock) {
            if (ValueObject.value.equals("")) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("get的值是 " + ValueObject.value);
            ValueObject.value = "";
            lock.notify();
        }
    }
}

class ThreadP extends Thread {
    private P p;

    public ThreadP(P p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true) {
            p.setValue();
        }
    }
}

class ThreadC extends Thread {
    private C c;

    public ThreadC(C c) {
        this.c = c;
    }

    @Override
    public void run() {
        while (true) {
            c.getValue();
        }
    }
}

class PCTest {
    public static void main(String[] args) {
        String lock = new String("");
        P p = new P(lock);
        C c = new C(lock);
        ThreadP threadP = new ThreadP(p);
        ThreadC threadC = new ThreadC(c);
        threadC.start();
        threadP.start();
    }
}