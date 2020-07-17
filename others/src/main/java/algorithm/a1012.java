package main.java.algorithm;

/**
 * @Auth justinniu
 * @Date 2018/10/12
 * @Desc
 */
public class a1012 {
    private static int i = 0;
    private static volatile boolean state = true;
    public static void Increase() {
        i++;
    }
    public static void Print(String a) {
        if(a.equals("A")) {
            Increase();
            System.out.println("A:" + i);
        }
        System.out.println("B:" + i);
    }

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            while (state) {
                Print("A");
                state = false;
            }
        });
        Thread b = new Thread(() -> {
            while (!state) {
                Print("B");
                state = true;
            }
        });
        a.start();
        b.start();
    }
}
