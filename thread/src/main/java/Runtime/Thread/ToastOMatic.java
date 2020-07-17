package main.java.Runtime.Thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class Toast {
    public enum Status {DRY, BUTTERED, JAMMED}
    private Status status = Status.DRY;
    private final int id;
    public Toast(int id) {this.id = id;}
    public void butter() {status = Status.BUTTERED;}
    public void jum() {status = Status.JAMMED;}

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }
    public String toString() {
        return "Toast " + id + ": " + status;
    }
}
class ToastQueue extends LinkedBlockingQueue<Toast> {}
class Toaster implements Runnable {

    private ToastQueue toastQueue;
    private int count = 0;
    private Random random = new Random(47);
    public Toaster(ToastQueue tq) { toastQueue = tq;}
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
                Toast t = new Toast(count++);
                toastQueue.put(t);
            }
        }catch (InterruptedException e) {
            System.out.println("ERRER");
        }
        System.out.println("Toaster off");
    }
}
class Butterer implements Runnable {

    private ToastQueue dryQueue, buttererQueue;
    private int count = 0;
    private Random random = new Random(47);

    public Butterer(ToastQueue dryQueue, ToastQueue buttererQueue) {
        this.dryQueue = dryQueue;
        this.buttererQueue = buttererQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                buttererQueue.put(t);
            }
        }catch (InterruptedException e) {
            System.out.println("ERRER");
        }
        System.out.println("Butterrd off");
    }
}
class Jammer implements Runnable {

    private ToastQueue finishedQueue, buttererQueue;
    private int count = 0;
    private Random random = new Random(47);

    public Jammer(ToastQueue buttererQueue, ToastQueue finishedQueue) {
        this.finishedQueue = finishedQueue;
        this.buttererQueue = buttererQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
                Toast t = buttererQueue.take();
                t.jum();
                System.out.println(t);
                finishedQueue.put(t);
            }
        }catch (InterruptedException e) {
            System.out.println("ERRER");
        }
        System.out.println("Jammer off");
    }
}
class Eater implements Runnable {

    private ToastQueue finishedQueue;
    private int count = 0;
    private Random random = new Random(47);
    public Eater(ToastQueue tq) { finishedQueue = tq;}
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = finishedQueue.take();
                if(t.getId() != count++ || t.getStatus() != Toast.Status.JAMMED)
                    System.out.println("ERROR " + t);
                else
                    System.out.println("Chomp !" + t);
            }
        }catch (InterruptedException e) {
            System.out.println("ERRER");
        }
        System.out.println("Eater off");
    }
}
public class ToastOMatic {
    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue();
        ToastQueue butteredQueue = new ToastQueue();
        ToastQueue finishedQueue = new ToastQueue();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Toaster(dryQueue));
        service.execute(new Butterer(dryQueue, butteredQueue));
        service.execute(new Jammer(butteredQueue, finishedQueue));
        service.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        service.shutdownNow();
    }
}
