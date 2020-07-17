package main.java.proxy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test111 {
    public static void main(String[] args) {
//        String a = "zwt       1    2   3       123123 ";
//        System.out.println(a.replaceAll("\\s{1,}", " "));
        List<String> ARGS = Arrays.asList("-a", "-b", "-c");
        Set<String> tt = new HashSet<>();
        tt.add("-a");
        tt.add("-b");
        System.out.println(ARGS.containsAll(tt));
    }
}
