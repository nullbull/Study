package main.java.proxy;

import java.io.*;

/**
 * @Auth justinniu
 * @Date 2018/8/26
 * @Desc
 */
public class Persion implements Serializable {
    private int age;
    private String name;
    private String sex;

    public Persion(int age, String name, String sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Persion niu = new Persion(22, "niu", "nan");
        File file = new File("D:\\test.txt");
        if (! file.exists() ) file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(niu);
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
        Persion persion = (Persion) inputStream.readObject();
        System.out.println(persion.getName());
    }
}
