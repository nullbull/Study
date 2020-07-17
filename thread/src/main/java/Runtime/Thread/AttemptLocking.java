package main.java.Runtime.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();
    public void untimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock(): " + captured);
        } finally {
            if (captured)
                lock.unlock();
        }
    }
    public void timed() {
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try{
            System.out.println("tryLock(2, TimeUnit.SECONDS) : " + captured );
        }
        finally {
            if (captured)
                lock.unlock();
        }
    }

    static class Callzz implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("Callable");
            return "123";
        }
    }
    public static void main(String[] args) {
        final AttemptLocking al = new AttemptLocking();
        al.untimed();
        al.timed();
        new Thread() {
            {setDaemon(true);}
            public void run() {
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
       // Thread.yield();
        al.untimed();
        al.timed();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute((Runnable) new Callzz());
       Runnable a = () -> {
           System.out.println("");
       };
    }

}
