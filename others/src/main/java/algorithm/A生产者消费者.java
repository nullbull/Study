package main.java.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 牛贞昊（niuzhenhao@58.com）
 * @date 2019/2/12 15:23
 * @desc
 */
public class A生产者消费者 {
    public static Queue<Integer> q = new LinkedList<>();
    public static volatile boolean mark = false;
    public static int count = 0;
    public synchronized  void put() throws InterruptedException {
        while (!mark) {
            q.add(count++);
            System.out.println("生产了： " + count );
            Thread.sleep(1000);
            notifyAll();
            mark = true;
        }
    }
    public synchronized void take() throws InterruptedException {
        while (mark) {
            Thread.sleep(1000);
            System.out.println("消费了：" + q.poll());
            mark = false;
            wait();
        }
    }

    public static void main(String[] args) {
        A生产者消费者 t = new A生产者消费者();
        Thread a = new Thread(() -> {
            while (true) {
                try {
                    t.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread b = new Thread(() -> {
            while (true) {
                try {
                    t.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        b.start();
        a.start();
    }
}
