package main.java.SpringAOP;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auth justinniu
 * @Date 2018/8/23
 * @Desc
 */
public class Proxy<T> implements Subject {

    private T object;
    public Proxy(T t) throws IllegalAccessException, InstantiationException {
        object = t;
    }
    @Override
    public void request() {
        System.out.println("before");
        try {
            Method method = object.getClass().getDeclaredMethod("request");
            method.invoke(object);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }   finally {
            System.out.println("after");
        }
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Subject subject = new Proxy(new RealSubject());
        subject.request();
    }
}
