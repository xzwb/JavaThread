package thread2;

/**
 * 解决同步死循环
 * 更改后添加多线程技术
 */
public class PrintString implements Runnable {
    private boolean isPrint = true;

    public boolean isPrint() {
        return isPrint;
    }

    public void setPrint(boolean print) {
        isPrint = print;
    }

    public void method() {
        while (isPrint) {
            System.out.println("run printmethod threadname = " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        method();
    }
}

class PrintRun {
    public static void main(String[] args) {
        PrintString printString = new PrintString();
//        printString.method();
        new Thread(printString).start();
        System.out.println("我要停止他 stopmethod = " + Thread.currentThread().getName());
        printString.setPrint(false);
    }
}