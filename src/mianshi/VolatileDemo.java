package mianshi;

class MyData {
    volatile int num = 0;

    public void addTo60() {
        this.num = 60;
    }
}

/**
 * 1. 验证volatile的可见性
 */

public class VolatileDemo {
    public static void main(String[] args) {
        MyData data = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t update value");
        }, "AAA").start();

        // 第二个线程是main线程
        while (data.num == 0) {

        }

        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}
