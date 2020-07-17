package main.java.Runtime.Thread;

import java.util.concurrent.TimeUnit;

class NeedsCleanup{
    private final int id;

    NeedsCleanup(int id) {
        this.id = id;
        System.out.println("NeedCleanup " + id);
    }
    public void cleanup() {
        System.out.println("Cleaning up " + id);
    }
}
class Blocked3 implements Runnable {
    private volatile double d = 0.0;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                NeedsCleanup n1 = new NeedsCleanup(1);
                try {
                    System.out.println("Sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    try {
                        System.out.println("Calculating");
                        for (int i = 1; i < 250000; i++)
                            d = d + (Math.PI + Math.E) / d;
                        System.out.println("Finised time-consuming operation");
                    } finally {
                        n2.cleanup();
                    }
                } finally {
                    n1.cleanup();
                }
            }
            System.out.println("Exit while");
        } catch (InterruptedException e) {
            System.out.println("Exiting InterruptedException");
        }
    }
}
public class InterruptingIdiom {
    public static void main(String[] args) throws InterruptedException {
 /*       if(args.length != 1 ) {
            System.out.println("usage: java InterruptingIdiom");
            System.exit(0);
        }*/
        Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
        t.interrupt();
    }
    }
