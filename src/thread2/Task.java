package thread2;

/**
 * synchronized 弊端
 * 这样子执行下来是6秒使用同步代码块 可以减少到3秒
 */
public class Task {
    private String task1;
    private String task2;
    public /*synchronized*/ void doLongTime() {
        System.out.println("begin.....");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String t1 = "长时间处理返回1的值" + Thread.currentThread().getName();
//        task1 = "长时间处理返回1的值" + Thread.currentThread().getName();
        String t2 = "长时间处理返回2的值" + Thread.currentThread().getName();
//        task2 = "长时间处理返回2的值" + Thread.currentThread().getName();
        synchronized (this) {
            task1 = t1;
            task2 = t2;
        }
        System.out.println(task1 + "  " + Thread.currentThread().getName());
        System.out.println(task2 + "  " + Thread.currentThread().getName());
        System.out.println("end>>>>>>>");
    }
}


class CommonUtils {
    public static long beginTime;
    public static long endtime;
    public static long begin1;
    public static long end1;
}

class MyThread extends Thread {
    private Task task;

    public MyThread(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        CommonUtils.beginTime = System.currentTimeMillis();
        task.doLongTime();
        CommonUtils.endtime = System.currentTimeMillis();
    }
}

class MyThread1 extends Thread {
    private Task task;

    public MyThread1(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        CommonUtils.begin1 = System.currentTimeMillis();
        task.doLongTime();
        CommonUtils.end1 = System.currentTimeMillis();
    }
}

class TaskTest {
    public static void main(String[] args) {
        Task t = new Task();
        MyThread myThread = new MyThread(t);
        myThread.setName("a");
        myThread.start();
        MyThread1 myThread1 = new MyThread1(t);
        myThread1.setName("b");
        myThread1.start();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long begin = CommonUtils.beginTime;
        if (CommonUtils.beginTime > CommonUtils.begin1) {
            begin = CommonUtils.begin1;
        }
        long end = CommonUtils.endtime;
        if (CommonUtils.endtime < CommonUtils.end1) {
            end = CommonUtils.end1;
        }
        System.out.println("耗时 : " + ((end-begin)/1000));
    }
}