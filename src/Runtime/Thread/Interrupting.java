package Runtime.Thread;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class SleepBlocked implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
        System.out.println("Exiting SleepBlocked.run()");
    }
}
class IOBlocked implements Runnable {

    private InputStream in;
    public IOBlocked(InputStream in) {this.in = in;}

    @Override
    public void run() {
        try {
            System.out.println("Waiting for Read");
            in.read();
        } catch (IOException e) {
            if(Thread.currentThread().isInterrupted())
                System.out.println("Interrupted from blocked I/O");
            else
                throw new RuntimeException(e);
        }
        System.out.println("Exiting IOBlocked.run()");
    }
}
class SynchronizedBlocked implements Runnable{

    public synchronized void f() {
        while (true)
            Thread.yield();
    }

    public SynchronizedBlocked() {
        new Thread() {
            public void run() {
                f();
            }
        }.start();
    }

    @Override
    public void run() {
        System.out.println("Trying to call f()");
        f();
        System.out.println("Exiting SynchroizedBLocked.run()");
    }
}
public class Interrupting {
    public static ExecutorService executor = Executors.newCachedThreadPool();
    static void test(Runnable runnable) throws InterruptedException{
        Future<?> f = executor.submit(runnable);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupting: " + runnable.getClass().getName());
        f.cancel(true);
        System.out.println("Interrupt send to  " + runnable.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("xx");
        System.exit(0);
    }
}
