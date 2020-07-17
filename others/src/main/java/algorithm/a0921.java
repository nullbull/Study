package main.java.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auth justinniu
 * @Date 2018/9/21
 * @Desc
 */
public class a0921 {
    private Queue<Integer> queue = new LinkedList<>();

    public synchronized Integer get() {
        while (queue.isEmpty()) {
            try {
                System.out.println("现在为空，wait()");
                wait();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        System.out.println("有，拿走");
        int temp = queue.peek();
        queue.poll();
        return temp;
    }
    public synchronized void put(int i) {
        while (queue.size() > 10) {
            try {
                System.out.println("多了");
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("添加");
        queue.offer(i);
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        a0921 a0921 = new a0921();
        Thread a = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        a0921.put(i);
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        Thread b = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        System.out.println(a0921.get());
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        a.start();
        b.start();
    }
}
