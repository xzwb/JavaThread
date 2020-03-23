package thread5;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("执行任务, 时间为 : " + new Date());
    }
}

class Test {
    public static void main(String[] args) {
        System.out.println("当前时间为" + new Date());
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.SECOND, 10);
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND)-10);
        Date runDate = calendar.getTime();
        System.out.println(runDate);

        MyTask task = new MyTask();
        Timer timer = new Timer();
        timer.schedule(task, runDate);
    }
}
