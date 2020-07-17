package main.java.Collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Person implements Comparable<Person>{
    private String name;

    private Integer age;
    public Person(String name, Integer age) {
        this.age = age;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return this.age > o.age ? -1 : this.age.equals(o.age) ? 0 : 1 ;
    }

    public static void main(String[] args) {
        Person[] niu = {new Person("niu", 22), new Person("li", 42), new Person("zhang", 20)};
        List<Person> zz = Arrays.asList(niu);
        Collections.sort(zz);
        for (Person p : zz)
            System.out.println(p);
    }
}
