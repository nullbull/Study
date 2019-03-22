package algorithm;

/**
 * @author 牛贞昊（niuzhenhao@58.com）
 * @date 2019/2/12 15:39
 * @desc
 */
public class A交替打印2 {
    private static int count = 0;
    private static Object lock = new Object();
    public synchronized void print(String name) throws InterruptedException {
        notify();
        System.out.println(name + " 打印 " + count++);
        wait();
    }

        Thread b = new Thread(() -> {
            while (count < 100) {
                try {
                    this.print("B");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    Thread a = new Thread(() -> {
        while (count < 100) {
            try {
                this.print("A");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    public static void main(String[] args) {
        A交替打印2 t = new A交替打印2();
        t.a.start();
        t.b.start();
    }
}
