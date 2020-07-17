package main.java.SpringAOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auth justinniu
 * @Date 2018/8/23
 * @Desc
 */
public class JdkProxy implements InvocationHandler {
    private Object object;
    public JdkProxy(Object o) {
        object = o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;
        try {
            System.out.println("before");
            result = method.invoke(object, args);
        } catch (Exception e) {
            System.out.println("exception:" + e.getMessage());
        } finally {
            System.out.println("after");
        }
        return result;
    }

    public static void main(String[] args) {
        Subject subject = (Subject) Proxy.newProxyInstance(JdkProxy.class.getClassLoader(), new Class[]{Subject.class}, new JdkProxy(new RealSubject()));
        subject.request();

    }

}
