package main.java.IO;

import java.lang.reflect.*;

public class ToyRefection {
    public static void printInfo(String info, Object obj) {
        if (obj.getClass().isArray()) {
            System.out.println(info + ": ");
            int length = Array.getLength(obj);
            System.out.println("Array Size: " + length);
            for (int i = 0; i < length; i++) {
                System.out.print("Array[" + i + "]: " + Array.get(obj, i) + ", ");
            }
            if (length != 0)
                System.out.println();
        }
        System.out.println(info + ": " + obj.toString());
    }
    public static void main(String[] args){
        try {
            Class<?> c = Class.forName("main.java.IO.Toy");
            printInfo("获得类对象", c);
            Class<?> superClass = c.getSuperclass();
            printInfo("获得父类", superClass);
            Class<?>[] interfaces = c.getInterfaces();
            printInfo("获得所有父接口", interfaces);
            Constructor<?>[] constructors = c.getConstructors();
            printInfo("获得够着方法", constructors);
            Constructor<?> constructor = c.getDeclaredConstructor(String.class,
                    String.class, int.class);
            Toy toy =(Toy)c.newInstance();
            printInfo("获得指定构造方法", constructor);
            Method method = c.getMethod("playBoy", String.class);
            printInfo("获得公有方法", method);
            method.invoke(toy, "niu");
            String modifer = Modifier.toString(method.getModifiers());
            printInfo("获取修饰符", modifer);
            Class<?>[] paramTypes = method.getParameterTypes();
            printInfo("获取参数类型", paramTypes);
            Class<?> returnType = method.getReturnType();
            printInfo("获取返回值类型", returnType);
            Class<?>[] excepTypes = method.getExceptionTypes();
            printInfo("获得异常类型", excepTypes);
            Method method1 = c.getDeclaredMethod("buildMsg", String.class);
            method1.setAccessible(true);
            String result = (String) method1.invoke(toy, "zhang");
            printInfo("获取私有方法", result);
            Field[] fields = c.getFields();
            printInfo("获得全部属性",  fields);
            Field field = c.getDeclaredField("name");
            printInfo("获得自身属性", field);
            Field field1 = c.getField("price");
            printInfo("获得公有属性", field1);

            String fieldModifier = Modifier.toString(field.getModifiers());
            printInfo("获得权限修饰符", fieldModifier);
            int[] exampleArray = {1,2,3,4,5};
            Class<?> componentType = exampleArray.getClass().getComponentType();
            printInfo("数组类型", componentType.getName());
            printInfo("数组长度", Array.getLength(exampleArray));
            printInfo("获取数组元素", Array.get(exampleArray, 2));
            Array.set(exampleArray, 1, 6);
            printInfo("修改数组元素", exampleArray);
            printInfo("获取当前前类加载器", toy.getClass().getClassLoader().getClass().getName());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
