package com.shebao.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auth justinniu
 * @Date 2018/8/24
 * @Desc
 */
@SpringBootApplication
public class Order {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        ExecutorService service2 = Executors.newFixedThreadPool(1);
        service2.execute(()-> {
            try {
                Thread.sleep(1000);
                System.out.println("AAAAAAAAAAA");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service2.execute(()-> {
            try {
                Thread.sleep(1000);
                System.out.println("BBBBBBBBBBBB");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service2.execute(()-> {
            try {
                Thread.sleep(1000);
                System.out.println("CCCCCCCCCCC");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
    }
}
