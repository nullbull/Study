package main.java.Runtime.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {

    private IntGenerator intGenerator;
    private final int id;
    public  EvenChecker(IntGenerator generator, int id) {
        this.id = id;
        this.intGenerator = generator;
    }
    @Override
    public void run() {
        while (!intGenerator.isCanceled()) {
            int val = intGenerator.next();
            if(val % 2 != 0) {
                System.out.println(val + " not Even!");
                intGenerator.cancel();
            }
        }
    }
    public static void test(IntGenerator it, int n) {
        System.out.println("press zz to exit");
        ExecutorService service = Executors.newCachedThreadPool();
        for( int i = 0; i < n; i++)
            service.execute(new EvenChecker(it, 1));
        service.shutdown();//不允许其他线程提交
    }
    public static void test(IntGenerator it) {
        test(it, 10);
    }
}
class EvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;
    @Override
    public int next() {
        ++currentEvenValue;
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
