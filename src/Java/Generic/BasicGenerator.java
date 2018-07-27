package Java.Generic;

class CountedObject{
    private static int count = 0;
    private final int id = count++;
    public int id() { return  id;}

    @Override
    public String toString() {
        return "CountObject " + id;
    }
}
public class BasicGenerator<T>  implements Generator<T>{

    public Class<T> type;
    public BasicGenerator(Class<T> tClass) {
        this.type = tClass;
    }
    @Override
    public T next() {
        try {
            return type.newInstance();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static <T>BasicGenerator<T> create(Class<T> type) {
        return new BasicGenerator<T>(type);
    }
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Generator<CountedObject> generator = BasicGenerator.create(CountedObject.class);
        for(int i = 0; i < 5; i++)
            System.out.println(generator.next());
    }
}
