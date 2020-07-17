package Runtime.Thread;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {

    private Random random = new Random(47);
    private static int counter = 0;
    private final int id = counter++;
    private final int priority;
    private static List<PrioritizedTask> sequence = new ArrayList<>();
    PrioritizedTask(int piority) {
        this.priority = piority;
        sequence.add(this);
    }

    @Override
    public int compareTo(PrioritizedTask o) {
        return priority > o.priority ? 1 : (priority  < o.priority ? -1 : 0);
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(250));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("[%1$-3d]", priority) + " Task " + id;
    }
    public String summary() {
        return "(" + id +  ":" + priority + ")";
    }
    public static class EndSentinel extends PrioritizedTask {
        private ExecutorService executorService;
        public EndSentinel(ExecutorService e) {
            super(-1);
            this.executorService = e;
        }
        public void run() {
            int count = 0;
            for (PrioritizedTask pt : sequence) {
                System.out.print(pt.summary() + " ");
                if (++count % 5 ==0)
                    System.out.println();
            }
            System.out.println();
            System.out.println(this + " Calling shutdownNow()");
            executorService.shutdownNow();

        }
    }
}
class ProiritizedTaskProducer implements Runnable {
    private Random random = new Random(47);
    private Queue<Runnable> queue;
    private ExecutorService executorService;

    public ProiritizedTaskProducer(Queue<Runnable> queue, ExecutorService executorService) {
        this.queue = queue;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        for(int i = 0; i < 20; i++) {
            queue.add(new PrioritizedTask(random.nextInt(10)));
            //Thread.yield();
        }
        try {
            for (int i = 0; i < 10; i++)
            {

                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizedTask(10));
            }
            queue.add(new PrioritizedTask.EndSentinel(executorService));
        }catch (InterruptedException e) {
            System.out.println("ERROR");
        }
        System.out.println("Finished ProiritizedTaskProducer");
        System.out.println(queue.getClass().getName());
    }
}
class ProritizedTaskConsumer implements Runnable {
    private PriorityBlockingQueue<Runnable> q;
    public ProritizedTaskConsumer(PriorityBlockingQueue<Runnable> q) {
        this.q = q;
        System.out.println(q.size());

    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted())
                q.take().run();
        }catch (InterruptedException e) {
            System.out.println("ERROR");
        }
        System.out.println("Finished Consumer");
    }
}
public class PriotityBlockingQueueDemo {
    public static void main(String[] args) {
        Random random = new Random(47);
        ExecutorService executorService = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();
        executorService.execute(new ProiritizedTaskProducer(queue, executorService));
        System.out.println(queue.size()+ "-----------------------");
        executorService.execute(new ProritizedTaskConsumer(queue));
    }
}
