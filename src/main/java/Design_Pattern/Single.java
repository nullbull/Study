package Design_Pattern;

public class Single {
    private static Single single = new Single();
    private Single() {
        System.out.println("单利模式");
    }
    public static Single getSingle() {
        return single;
    }

    public static void main(String[] args) {
        Single.getSingle();
    }
}
