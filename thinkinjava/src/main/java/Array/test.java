package Java.Array;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        char[] c = new char[10];
        for(int i = 0; i < 10; i++)
            System.out.println(c[i]);
        Map<String, String>  zz =  new HashMap<>();
        zz.put("zhang", "niu");
        for(Map.Entry entry : zz.entrySet()) {
            System.out.println(entry.getKey() + " "  + entry.getValue());
        }
    }
}
