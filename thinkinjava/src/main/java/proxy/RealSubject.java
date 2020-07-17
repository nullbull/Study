package test;

/**
 * @Auth justinniu
 * @Date 2018/8/23
 * @Desc
 */
public class RealSubject implements Subject{
    @Override
    public void request() {
        System.out.println("真的 subject execute request");
    }
    public void hello() {
        System.out.println("Hello, it's me");
    }
}
