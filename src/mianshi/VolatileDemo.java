package mianshi;

import java.util.concurrent.atomic.AtomicInteger;

class MyData {
    volatile int num = 0;

    public void addTo60() {
        this.num = 60;
    }

    public void addPlusPlus() {this.num++;}

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1. 验证volatile的可见性
 *
 * 2. volatile不保证原子性
 */

public class VolatileDemo {
    /**
     * volatile可见性
     */
    public static void main(String[] args) {
        MyData data = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            data.addTo60();
            data.addAtomic();
            System.out.println(Thread.currentThread().getName() + "\t update value");
        }, "AAA").start();

        // 第二个线程是main线程
        while (data.atomicInteger.get() != 1) {

        }

        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }

    /**
     * 原子性
     * @param args
     */
//    public static void main(String[] args) throws InterruptedException {
//        MyData data = new MyData();
//
//        for (int i = 0; i < 20; i++) {
//            new Thread(() -> {
//                for (int j = 0; j < 1000; j++) {
////                    data.addPlusPlus();
//                    data.addAtomic();
//                }
//            }, String.valueOf(i)).start();
//        }
//        // 等待20个线程都加完了以后
//        Thread.sleep(3000);
////        System.out.println(data.num);
//        System.out.println(data.atomicInteger);
//    }
}
