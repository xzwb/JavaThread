package thread3;

/**
 * join方法
 */
public class MyJoin extends Thread {
    @Override
    public void run() {
        int value = 1000;
        System.out.println(value);
        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Test5 {
    public static void main(String[] args) {
        MyJoin join = new MyJoin();
        join.start();
        try {
            join.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("zzzz");
    }
}
