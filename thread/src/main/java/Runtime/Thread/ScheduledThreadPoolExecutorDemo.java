package main.java.Runtime.Thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Auth justinniu
 * @Date 2018/8/9
 * @Desc
 */
public class ScheduledThreadPoolExecutorDemo {
    static class TimerTask implements Runnable{
        private String id;
        public TimerTask(String id){
            this.id = id;
        }
        @Override
        public void run(){
            System.out.println(id);
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
        ScheduledFuture sfa = ses.scheduleAtFixedRate(new TimerTask("a"), 200,
                1000, TimeUnit.MILLISECONDS);
        ScheduledFuture sfb = ses.scheduleAtFixedRate(new TimerTask("b"), 400,
                1000, TimeUnit.MILLISECONDS);
        ScheduledFuture sfc = ses.scheduleAtFixedRate(new TimerTask("c"), 600,
                1000, TimeUnit.MILLISECONDS);
        ScheduledFuture sfd = ses.scheduleAtFixedRate(new TimerTask("d"), 800,
                1000, TimeUnit.MILLISECONDS);
        Thread.sleep(5000);
        sfa.cancel(true);
        Thread.sleep(5000);
        ses.shutdown();
    }
}