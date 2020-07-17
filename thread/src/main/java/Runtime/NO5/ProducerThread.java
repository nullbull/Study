package Runtime.NO5;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class ProducerThread extends Thread {
    private final Exchanger<char[]> exchanger;
    private char[] buffer = null;
    private int index = 0;
    private final Random random;

    public ProducerThread(Exchanger<char[]> exchanger, char[] buffer, long seed) {
        super("ProducerThread");
        this.exchanger = exchanger;
        this.buffer = buffer;
        this.random = new Random(seed);
    }
    public void run() {

        try {
            while (true) {
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = newChar();
                    System.out.println(Thread.currentThread().getName() + " : " + buffer[i] + " -> ");

                }
                System.out.println(Thread.currentThread().getName() + ":Before Exchange");
                buffer = exchanger.exchange(buffer);
                System.out.println(Thread.currentThread().getName() + ":After Exchange");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private char newChar() throws InterruptedException {
        char c = (char) ('A' + index);
        index++;
        Thread.sleep(random.nextInt(1000));
        return c;
    }

}
