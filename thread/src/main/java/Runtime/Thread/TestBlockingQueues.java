package Runtime.Thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

class LiftOffRunner implements Runnable {

    private BlockingQueue<LiftOff> rockets;
    public LiftOffRunner(BlockingQueue<LiftOff> queue) {
        this.rockets = queue;
    }
    public void add(LiftOff liftOff) {
        try {
            rockets.put(liftOff);
        } catch (InterruptedException e) {
            System.out.println("ERROR");
        }
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                LiftOff rocket = rockets.take();
                rocket.run();
            }
        } catch (InterruptedException e) {
            System.out.println("ERROR");
        }
        System.out.println("Exit Run()");
    }
}
public class TestBlockingQueues {
    static void getkey() {
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void getkey(String name) {
        System.out.println(name);
        getkey();
    }
    static void test(String msg, BlockingQueue<LiftOff> queue) {
        System.out.println(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        Thread t = new Thread(runner);
        t.start();
        for (int i = 0; i < 5; i++) {
            runner.add(new LiftOff(5));
        }
        getkey("Press enter " + msg + ")");
        t.interrupt();
        System.out.println("Finished " + msg + " test");
    }

    public static void main(String[] args) {
        test("LinkedBlockQueue", new LinkedBlockingQueue<LiftOff>() );
        test("ArrayBlockQueue", new ArrayBlockingQueue<LiftOff>(5) );
        test("SynchrolockQueue", new SynchronousQueue<LiftOff>() );
    }
}
