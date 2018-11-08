package test;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.*;

/**
 * @Auth justinniu
 * @Date 2018/8/23
 * @Desc
 */
public class test {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis() /1000);
        ThreadFactory threadFactory = new ThreadPoolTaskScheduler();

        ExecutorService service = Executors.newScheduledThreadPool(10, threadFactory);

//        Runnable a = () -> {
//            for (int i = 0; i < 10000; i++) {
//                try {
//                    Thread.sleep(100);
//                    System.out.println("Thread B : " + i);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        Thread aa = new Thread(a);
//        service.execute(aa);
//        service.execute(() -> {
//
//            for (int i = 0; i < 10000; i++) {
//                    try {
//                        aa.join();
//                        Thread.sleep(100);
//                        System.out.println("Thread A : " + i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//        }) ;
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Runnable b = () -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("await()");
        };
        service.execute(b);
        for (int i = 0; i < 10; i++) {
            Runnable a = new Runnable() {
                @Override
                public void run() {
                    countDownLatch.countDown();
                    System.out.println( " countdown ");
                }
            };
            ((ScheduledExecutorService) service).scheduleAtFixedRate(a, 10, 1000, TimeUnit.MILLISECONDS);
        }



    }



    }




