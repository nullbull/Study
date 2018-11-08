package test;

/**
 * @Auth justinniu
 * @Date 2018/8/24
 * @Desc
 */
public class single {
    private static volatile single instance;
    private single() {}

    public static single getInstance() {
        if (instance == null) {
            synchronized (single.class) {
                if (instance == null)
                    return new single();
            }
        }
        return instance;
    }
}
