package Runtime.NO5;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class ConsumerThread extends Thread {
    private final Exchanger<char[]> exchanger;
    private final Random random ;
    private int index = 0;
    private char[] buffer = null;
    public ConsumerThread(Exchanger<char[]> exchanger, char[] buffer, long seed) {
        super("ConsumerThread");
        this.exchanger = exchanger;
        this.buffer = buffer;
        this.random = new Random(seed);
    }
    public void run() {
        try {
            while (true) {
                System.out.println(Thread.currentThread().getName() + ":Before exchange");
                buffer = exchanger.exchange(buffer);
                System.out.println(Thread.currentThread().getName() + ":After exchange");
                for(int i = 0; i < buffer.length; i++) {
                    System.out.println(Thread.currentThread().getName() + ": -> " + buffer[i]);
                    Thread.sleep(random.nextInt(1000));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Exchanger<char[]> exchanger = new Exchanger<>();
        char[] char1 = new char[10];
        char[] char2 = new char[10];
        new ProducerThread(exchanger, char1,  314159).start();
        new ConsumerThread(exchanger, char2, 55555).start();
     }
}
