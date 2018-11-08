//package test;
//
//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;
//
//import java.lang.reflect.Method;
//
///**
// * @Auth justinniu
// * @Date 2018/8/23
// * @Desc
// */
//public class DemoMethodInterceptor implements MethodInterceptor {
//    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//        System.out.println("before ");
//        Object result = null;
//        try {
//            result = proxy.invokeSuper(obj, args);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        } finally {
//            System.out.println("after");
//        }
//        return result;
//    }
//
//    public static void main(String[] args) {
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(RealSubject.class);
//        enhancer.setCallback(new DemoMethodInterceptor());
//        RealSubject realSubject = (RealSubject) enhancer.create();
//        realSubject.hello();
//    }
//}
