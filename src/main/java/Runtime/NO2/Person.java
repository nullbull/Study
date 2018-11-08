package Runtime.NO2;

public final class Person {
    private final String name;
    private final String address;
    private static final int zz;
    static {
        zz = 3;
    }
    public Person(String name, String address){
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "[ Person: name = " + name + ", address = " + address + "]" ;
    }
}
