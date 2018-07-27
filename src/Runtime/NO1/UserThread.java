package Runtime.NO1;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Log{
    public static void println(String s){
        System.out.println(Thread.currentThread().getName() + ": " + s);
    }
}
class BoundedResource {
    private final Semaphore semaphore;
    private final int permits;
    private final static Random random = new Random(47);
    public BoundedResource(int permits){
        this.semaphore = new Semaphore(permits);
        this.permits = permits;
    }
    public void use() throws InterruptedException{
        semaphore.acquire();
        try {
            doUse();
        } finally {
            semaphore.release();
        }
    }

    private void doUse() throws InterruptedException{
        Log.println("Begin : used = " + (permits - semaphore.availablePermits()));
        Thread.sleep(random.nextInt(500));
        Log.println("END:  used = " + (permits - semaphore.availablePermits()));
    }
}
public class UserThread extends Thread {
//    private final Gate gate;
//    private final String name;
//    private final String myaddress;
//    public UserThread(Gate gate, String name, String myaddress){
//        this.gate = gate;
//        this.name = name;
//        this.myaddress = myaddress;
//    }
/*    public void run(){
        System.out.println(name + " BEGIN");
        while (true){
            gate.pass(name, myaddress);
        }
    }*/
    private final static Random random = new Random(47);
    private final BoundedResource resource;
    public UserThread(BoundedResource boundedResource){
        this.resource = boundedResource;
    }
    public void run() {
        try {
            while (true) {
                resource.use();
                Thread.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        BoundedResource boundedResource = new BoundedResource(3);
        for (int i = 0; i < 3; i++) {
            new UserThread(boundedResource).start();
        }
    }
}
