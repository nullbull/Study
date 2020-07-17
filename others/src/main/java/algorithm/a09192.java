package main.java.algorithm;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auth justinniu
 * @Date 2018/9/19
 * @Desc
 */
public class a09192 {
    public static synchronized void put(LinkedList list, String a) {
        list.add(a);
    }
    public static synchronized void get(LinkedList list) {
        System.out.println(list.getFirst());
    }
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
        ExecutorService service = Executors.newFixedThreadPool(3);
        CountDownLatch cd = new CountDownLatch(3);

        service.execute(() -> {
            try {
                System.out.println("t1" + "execute");
                Thread.sleep(100);
                put(list, "A");
                while (list.size() >= 9) {
                    list.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.execute(() -> {
            try {
                System.out.println("t2" + "execute");
                Thread.sleep(200);
                put(list, "B");
                while (list.size() >= 9) {
                    list.wait();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.execute(() ->{
            try {
                System.out.println("t3" + "execute");
                Thread.sleep(300);
                put(list, "C");
                while (list.size() >= 9) {
                    list.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            cd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t4 =  new Thread(() ->{
            try {
                System.out.println("t4" + "execute");
                Thread.sleep(15);
                get(list);
                while (list.size() == 0) {
                    list.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t4.start();
    }

}
