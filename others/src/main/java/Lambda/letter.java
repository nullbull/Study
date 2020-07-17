package main.java.Lambda;

import java.util.function.Function;

public class letter {
    public static String addHeader(String text) {
        return "From niu " + text;
    }
    public static String addFooter(String text) {
        return text + "Kind regards";
    }
    public static String checkSpelling(String text) {
        return text.replaceAll("lada", "lambda");
    }

    public static void main(String[] args) {
        Function<String, String> a = letter::addFooter;
        Function<String, String> b = a.andThen(letter::addFooter);
        b.apply("niu");

    }



}
