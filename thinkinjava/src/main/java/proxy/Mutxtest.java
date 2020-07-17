package main.java.proxy;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Auth justinniu
 * @Date 2018/8/24
 * @Desc
 */
public class Mutxtest {
    static int a = 0;
   static      Mutx mutx = new Mutx();
    public static void main(String[] args) {

        CyclicBarrier cy = new CyclicBarrier(10, () -> {
            System.out.println(" a : " + a);
            a = 0;
        });

        for (int i = 0; i < 10; i++) {
            Thread a = new Thread(() -> {
                for (int j = 0; j < 10000; j++)
                    increase();
                try {
                    System.out.println(Thread.currentThread().getId() + " is running");
                    cy.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            a.start();
        }
//        cy.reset();
//        a = 0;
//
//        for (int i = 0; i < 30; i++) {
//            Thread a = new Thread( () -> {
//                for (int j = 0; j < 10000; j++) {
//                    increase2();
//                }
//
//                try {
//                    System.out.println(Thread.currentThread().getId() + "is running");
//                    cy.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (BrokenBarrierException e) {
//                    e.printStackTrace();
//                }
//            });
//            a.start();
//        }

    }
    public static void increase() { a++;}
    public static void increase2() { mutx.lock();
    a++; mutx.unLock();}
}
