package Runtime.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

class DelayedTask implements Runnable, Delayed {

    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;
    protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();
    public DelayedTask(int n) {
        delta = n;
        trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
        sequence.add(this);
    }

    public long getTrigger() {
        return trigger;
    }
    public int compareTo(Delayed arg) {
        DelayedTask task = (DelayedTask) arg;
        if(trigger < task.trigger) return -1;
        if(trigger > task.trigger) return 1;
        return 0;
    }

    @Override
    public void run() {
        System.out.println(this + "");
    }

    @Override
    public long getDelay(TimeUnit unit) {
       return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
    }
    public String summary() {
        return "( " + id + ":" + delta + " )";
    }

    @Override
    public String toString() {
        return String.format("[%1$-4d]", delta) + " Task " + id;
    }
    public static class EndSentinel extends DelayedTask {
        private ExecutorService executorService;
        public EndSentinel(int delay, ExecutorService e) {
            super(delay);
            this.executorService = e;
        }
        public void run() {
            for (DelayedTask pt : sequence) {
                System.out.println(pt.summary() + " ");
            }
            System.out.println();
            System.out.println(this + " Calling shutdownNow()");
            executorService.shutdownNow();

        }
    }
}
class DelayedTaskConsumer implements Runnable {

    private DelayQueue<DelayedTask> q;
    public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
        this.q = q;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted())
                q.take().run();
        }catch (InterruptedException e) {
            System.out.println("ERROR");
        }
        System.out.println("Finished DelayedTakConsumer");
    }
}
public class DalayQueue {
    public static void main(String[] args) {
        Random random = new Random(47);
        ExecutorService service = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.put(new DelayedTask(random.nextInt(500)));
        }
        queue.add(new DelayedTask.EndSentinel(5000, service));
        service.execute(new DelayedTaskConsumer(queue));

    }
}
