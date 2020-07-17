package main.java.Runtime.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    public static synchronized int nextSerialNumber() {
        return serialNumber++;
    }
}
class CircularSet {
    private int[] array;
    private int length;
    private int index = 0;
    CircularSet(int size) {
        array = new int[size];
        length = size;
        for(int i = 0; i < size; i++) {
            array[i] = -1;
        }
    }
    public synchronized void add(int i) {
        array[index] = i;
        index = ++index % length;
    }
    public synchronized boolean contains(int val) {
        for(int i = 0; i < length; i++)
            if(array[i] == val) return true;
        return false;
    }
}
public class SerialNumberChecker {
    private static final int SIZE = 10;
    private static CircularSet serials = new CircularSet(1000);
    private static ExecutorService service = Executors.newCachedThreadPool();
    static class SerialChecker implements Runnable {
        @Override
        public void run() {
            while (true) {
                int serial = SerialNumberGenerator.nextSerialNumber();
                if (serials.contains(serial)) {
                    System.out.println("Duplicate :" + serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < SIZE; i++) {
            service.execute(new SerialChecker());
        }
        if(args.length > 0) {
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
            System.out.println("No duplicates detected");
            System.exit(0);
        }
    }
}
