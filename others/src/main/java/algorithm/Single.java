package main.java.algorithm;

/**
 * @Auth justinniu
 * @Date 2018/9/19
 * @Desc
 */
public class Single {
    private static volatile Single instance;
    private Single() throws Exception {
        if (instance != null) {
            throw new Exception("该对象以及存在");

        }
    }
    public static  Single getInstance() throws Exception {
        if (instance == null) {
            synchronized (Single.class) {
                if (instance == null) {
                    return new Single();
                }
            }
        }
        return instance;
    }

}
