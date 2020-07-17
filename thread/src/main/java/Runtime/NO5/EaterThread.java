package Runtime.NO5;

import java.util.Random;

public class EaterThread extends Thread {
    private  final Table table;
    private  final Random random;
    private static int id;
    public EaterThread(String name, Table table, long seed) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
    }
    public void run() {
        try {
            while (true) {
                String cake = table.take();
                Thread.sleep(random.nextInt(1000));
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
