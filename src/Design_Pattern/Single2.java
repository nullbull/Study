package Design_Pattern;
public class Single2 {
    private static Single2 single2;

    private Single2() {
        System.out.println("单利模式");
    }

    public static Single2 getSingle2() {
        if (single2 == null) {
            synchronized (Single2.class) {
                return new Single2();
            }
        }
        return single2;
    }

    public static void main(String[] args) {
        Single2.getSingle2();
    }
}