package Java.Generic;

interface Factory<T>{
    T creat();
}
class IntegerFactory implements Factory<Integer> {

    @Override
    public Integer creat() {
        return new Integer(0);
    }
}
class Foo<T> {
    T t;
    public <F extends Factory<T> > Foo(F factory) {
        t = factory.creat();
    }
}
public class FactoryConstraint {
}
