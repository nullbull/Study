package algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auth justinniu
 * @Date 2018/9/27
 * @Desc
 */
public class a0927 {
    static class Producer extends Thread {
        private Queue<String> q;
        public Producer(Queue<String> q) {
            this.q = q;
        }
        @Override
        public synchronized void run() {
            String[] strArr = new String[] {
                    "str1",
                    "str2",
                    "str3"
            };
            for (int i = 0; i < 666; ++i) {
                System.out.println(getName() + "生产者准备生产！");
                while (q.size() == 10) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(2000);
                    q.offer(strArr[i % 3]);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                notifyAll();
                System.out.println(getName() + "生产者生产完成：" + q);
            }
        }
    }

    static class Consumer extends Thread {
        private Queue<String> q;
        public Consumer(Queue<String> q) {
            this.q = q;
        }
        @Override
        public synchronized void run() {
            while (true) {
                System.out.println("消费者准备消费！");
                while (q.isEmpty()) {
                    try {
                        System.out.println("111");
                        wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(200);
                    q.remove();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                notifyAll();

                System.out.println(getName() + "消费者消费完成：" + q);
            }


        }
    }


        public static void main(String[] args) throws Exception {
            Queue<String> q = new LinkedList<>();
            new Producer(q).start();
            new Consumer(q).start();
        }


}
