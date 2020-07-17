package main.java.Runtime.Thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class TaskPortion implements Runnable {

    private static int counter = 0;
    private final int id = counter++;
    private static Random random = new Random(47);
    private final CountDownLatch latch;

    TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
            System.out.println(this + "interrupted");
        }
    }

    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
        System.out.println(this + " completed");
    }

    @Override
    public String toString() {
        return String.format("%1$-3d", id);
    }
}
     class WaitingTask implements Runnable {

        private static int counter = 0;
        private final int id = counter++;
        private final CountDownLatch latch;

        WaitingTask(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try{
                latch.await();
                System.out.println("lanch barrier passed for " + this);
            } catch (InterruptedException e) {
                System.out.println(this + "interrupted");
            }
        }

        @Override
        public String toString() {
            return String.format("WaitingTask %1$-3d", id);
        }
    }

public class CountDownLatchDemo {
    static final int size =100;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(size);
        for(int i = 0; i < 10; i++)
            exec.execute(new WaitingTask(latch));
        for(int i = 0; i < size; i++)
            exec.execute(new TaskPortion(latch));
        System.out.println("Launched all tasks");
       // exec.shutdownNow();
    }
}
