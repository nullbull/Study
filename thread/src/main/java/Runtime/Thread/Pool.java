package main.java.Runtime.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Pool<T> {
    private int size;
    private List<T> items = new ArrayList<>();
    private volatile boolean[] checkedOut;
    private Semaphore aviailable;
    public Pool(Class<T> tClass, int size) {
        this.size = size;
        checkedOut = new boolean[size];
        aviailable = new Semaphore(size, true);
        for (int i = 0; i < size; i++)
            try {
                items.add(tClass.newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
    }
    public T checkOut() throws InterruptedException {
        aviailable.acquire();
        return getItem();
    }
    public void checkIn(T x) {
        if(releaseItem(x))
            aviailable.release();
    }
    public synchronized T getItem() {
        for(int i = 0; i < size; ++i) {
            if(!checkedOut[i]) {
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        return null;
    }
    public synchronized boolean releaseItem(T item) {
        int index = items.indexOf(item);
        if(index == -1) return false;
        if(checkedOut[index]){
            checkedOut[index] = false;
            return true;
        }
        return false;
    }

}
    class Fat {
        private volatile double d;
        private static int counter = 0;
        private final int id = counter++;
        public Fat() {
            for(int i = 1; i < 10000; i++) {
                d += (Math.PI + Math.E) / (double) i;
             }
        }
        public void operation() {
            System.out.println(this);
        }

        @Override
        public String toString() {
            return "Fat: id: " + id;
        }
    }
    class CheckoutTask<T> implements Runnable{

        private static int counter = 0;
        private final int id = counter++;
        private Pool<T> pool;

        public CheckoutTask(Pool<T> pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            try {
                T item = pool.checkOut();
                System.out.println(this + " check out " + item);
                TimeUnit.SECONDS.sleep(1);
                System.out.println(this + "checking in" + item);
                pool.checkIn(item);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "CheckoutTask id : " + id ;
        }
    }
    class Demo{
        final static int SIZE = 25;
        public static void main(String[] args) throws Exception {
            final Pool<Fat> pool = new Pool<>(Fat.class, SIZE);
            ExecutorService exe = Executors.newCachedThreadPool();
            for(int i = 0; i < SIZE; i++) {
                exe.execute(new CheckoutTask<Fat>(pool));
            }
            System.out.println("All CheckoutTasks created");
            List<Fat> list = new ArrayList<>();
            for(int i = 0; i < SIZE; i++) {
                Fat f = pool.checkOut();
                System.out.println(i + ": main() thread checked out ");
                f.operation();
                list.add(f);
            }
            Future<?> blocked = exe.submit(new Runnable() {
                @Override
                public void run() {
                    try{
                        pool.checkOut();
                    } catch (InterruptedException e) {
                        System.out.println("checkout()  Interrupted" );
                    }
                }
            });
            TimeUnit.SECONDS.sleep(2);
            blocked.cancel(true);
            System.out.println("Checking in objects in " + list);
            for(Fat f : list)
                pool.checkIn(f);
            for (Fat f : list)
                pool.checkIn(f);
            exe.shutdownNow();
        }
    }