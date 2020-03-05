package thread2;

import java.util.ArrayList;
import java.util.List;

/**
 * 同步代码块的脏读
 */
public class MyOneList {
    private List list = new ArrayList();

    synchronized public void add(String data) {
        list.add(data);
    }

    synchronized public int getSize() {
        return list.size();
    }
}

class MyService {
    public MyOneList addService(MyOneList list, String data) {
//        if (list.getSize() < 1) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            list.add(data);
//        }
        /**
         * 防止脏读出现
         */
        synchronized (list) {
            if (list.getSize() < 1) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(data);
            }
        }
        return list;
    }
}

class ListThread1 extends Thread {
    private MyOneList list;

    public ListThread1(MyOneList list) {
        this.list = list;
    }

    @Override
    public void run() {
        MyService myService = new MyService();
        myService.addService(list, "a");
    }
}

class ListThread2 extends Thread {
    private MyOneList list;

    public ListThread2(MyOneList list) {
        this.list = list;
    }

    @Override
    public void run() {
        MyService myService = new MyService();
        myService.addService(list, "b");
    }
}

class ListTest {
    public static void main(String[] args) {
        MyOneList list = new MyOneList();
        ListThread1 thread1 = new ListThread1(list);
        ListThread2 thread2 = new ListThread2(list);
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("listsize = " + list.getSize());
    }
}