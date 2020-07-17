package main.java.Runtime.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicThreads {
    public static void main(String[] args){

//        for(int i = 0; i < 5; i++)
//            new Thread(new LiftOff()).start();
     //   ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i = 0; i< 5; i++)
            executorService.execute(new LiftOff());
        executorService.shutdown();
        System.out.println("Waiting for Liftoff");
    }
}
