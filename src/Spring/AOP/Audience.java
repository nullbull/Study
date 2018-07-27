//package Spring.AOP;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//
//@Aspect
//public class Audience {
//    //最原始的声明
///*    @Before("execution(* Spring.AOP.Performance.perform(..))")
//    public void silenceCellPhones(){
//        System.out.println("Silencing cell phones");
//    }
//    @Before("execution(* Spring.AOP.Performance.perform(..))")
//    public void takeSeats(){
//        System.out.println("Taking seats");
//    }
//    @AfterReturning("execution(* Spring.AOP.Performance.perform(..))")
//    public void applause() {
//        System.out.println("PA PA PA");
//    }
//    @AfterReturning("execution(* Spring.AOP.Performance.perform(..))")
//    public void demandRefund() {
//        System.out.println("Demanding a refund");
//    }*/
////一种更简单点的声明方法
//  @Pointcut("execution (* Spring.AOP.Performance.perform(..))")
//    public void performance() {}
//
//    @Before("performance()")
//    public void silenceCellPhones() {
//       System.out.println("Silencing cell phones");
//    }
//    @Before("performance()")
//    public void takeSeats() {
//       System.out.println("Silencing cell phones");
//    }
//    @AfterReturning("performance()")
//    public void applause() {
//       System.out.println("PA PA PA!!!");
//    }
//    @AfterThrowing("performance()")
//    public void demandRefund() {
//        System.out.println("Demanding a refund");
//    }
//
//   @Pointcut("execution (* Spring.AOP.Performance.perform(..))")
//   public void watchPerformance() {}
//   @Around("performance()")
//    public void watchPerformance(ProceedingJoinPoint pj) {
//       try {
//           System.out.println("Silencing cell phones");
//           System.out.println("Silencing cell phones");
//            pj.proceed();
//           System.out.println("PA PA PA!!!");
//       }catch (Throwable throwable){
//
//           throwable.printStackTrace();
//       }
//   }
//
//}
