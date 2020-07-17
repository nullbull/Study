package main.java.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auth justinniu
 * @Date 2018/10/14
 * @Desc
 */
public class a10143 {
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
                System.out.println("多了, wait()");
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        System.out.println("添加");
        queue.offer(i);
    }

    public static void main(String[] args) throws InterruptedException {
        a10143 a10143 = new a10143();
        Thread a = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        Thread.sleep(1000);
                        a10143.put(i);
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
                        Thread.sleep(1000);
                        System.out.println(a10143.get());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        b.start();
        a.start();
    }
}
