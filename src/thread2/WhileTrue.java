package thread2;

/**
 * 同步方法造成死循环
 * 线程b永远得不到执行的机会
 */
public class WhileTrue {
    synchronized public void methodA() {
        System.out.println("methodA begin");
        boolean isContinueRun = true;
        while (isContinueRun) {
        }
        System.out.println("methodA end");
    }

    synchronized public void methodB() {
        System.out.println("method begin");
        System.out.println("method end");
    }
}

class WhileTrueThreadA extends Thread {
    private WhileTrue whileTrue;

    public WhileTrueThreadA(WhileTrue whileTrue) {
        this.whileTrue = whileTrue;
    }

    @Override
    public void run() {
        whileTrue.methodA();
    }
}

class WhileTrueThreadB extends Thread {
    private WhileTrue whileTrue;

    public WhileTrueThreadB(WhileTrue whileTrue) {
        this.whileTrue = whileTrue;
    }

    @Override
    public void run() {
        whileTrue.methodB();
    }
}

class WhileTrueTest {
    public static void main(String[] args) {
        WhileTrue whileTrue = new WhileTrue();
        WhileTrueThreadA threadA = new WhileTrueThreadA(whileTrue);
        threadA.start();
        WhileTrueThreadB threadB = new WhileTrueThreadB(whileTrue);
        threadB.start();
    }
}
