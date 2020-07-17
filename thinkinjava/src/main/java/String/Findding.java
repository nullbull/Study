package Java.String;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Findding {
    public static void main(String[] args){
        Matcher matcher = Pattern.compile("[0-9]+")
                .matcher("adbc123def");
        while (matcher.find())
            System.out.println(matcher.group() + " ");
    }
}
