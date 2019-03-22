<<<<<<< HEAD
package SpringAOP;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auth justinniu
 * @Date 2018/8/23
 * @Desc
 */
public class DemoMethodInterceptor implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before ");
        Object result = null;
        try {
            result = methodProxy.invokeSuper(o, objects);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("after");
        }
        return result;
   }
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(new DemoMethodInterceptor());
        RealSubject realSubject = (RealSubject) enhancer.create();
        realSubject.hello();

    }
}
=======
package SpringAOP;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auth justinniu
 * @Date 2018/8/23
 * @Desc
 */
public class DemoMethodInterceptor implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before ");
        Object result = null;
        try {
            result = methodProxy.invokeSuper(o, objects);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("after");
        }
        return result;
   }
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(new DemoMethodInterceptor());
        RealSubject realSubject = (RealSubject) enhancer.create();
        realSubject.hello();

    }
}
>>>>>>> 144d81fb55995234a2dc56152af4a973caad6504
