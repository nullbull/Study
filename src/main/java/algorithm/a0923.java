package algorithm;

/**
 * @Auth justinniu
 * @Date 2018/9/23
 * @Desc
 */
public class a0923 {
    static class  Person {
        String name;
        public Person(String a) {
            this.name = a;
        }
    }
    public static void test(Person a) {
      a.name = "yangxu";
    }
    public static void main(String[] args) {
        Person a = new Person("niu");
        test(a);
        System.out.println(a.name);
    }
}
