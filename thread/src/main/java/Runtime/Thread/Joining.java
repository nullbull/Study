package main.java.Runtime.Thread;
class Sleeper extends Thread {
    private int duration;
    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }
    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted. " + " isInterrupted(): " + isInterrupted());
            return;
        }
        System.out.println(getName() + " has awakedned");
    }
}
class Joiner extends Thread {
    private Sleeper sleeper;;
    public Joiner(Sleeper sleeper, String name) {
        super(name);
        this.sleeper = sleeper;
        start();
    }
    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println(getName() + " join completed");
    }
}
public class Joining {
    public static void main(String[] args) {
        Sleeper sleeper = new Sleeper("Sleepy", 1500),
                grumpy = new Sleeper("Grumpy", 1500);
        Joiner dopey = new Joiner(sleeper,"Doopey"),
                doc = new Joiner(grumpy, "Doc");
        grumpy.interrupt();
    }
}
