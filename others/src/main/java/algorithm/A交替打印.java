package main.java.algorithm;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auth justinniu
 * @Date 2018/10/18
 * @Desc
 */
public class A交替打印 {
    public static volatile boolean mark = false;
    public static AtomicInteger i = new AtomicInteger(0);
    public static void Print() {
        System.out.println(Thread.currentThread().getName() +  "   ++++  " + i);
    }
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Thread A = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    lock.lock();
                    while (mark) {
                        try {
                            a.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Print();
                    i.set(i.get() + 1);
                    mark = true;
                    b.signal();
                    lock.unlock();
                }
            }
        };
        Thread B = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    lock.lock();
                    while (!mark) {
                        try {
                            b.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Print();
                    i.set(i.get() + 1);
                    mark = false;
                    a.signal();
                    lock.unlock();
                }
            }
        };
        A.start();
        B.start();

    }

}
