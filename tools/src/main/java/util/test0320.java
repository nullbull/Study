package main.java.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 牛贞昊（niuzhenhao@58.com）
 * @date 2019/3/20 16:48
 * @desc
 */
public class test0320 {
    public static void main(String[] args) {
        String regx = "(\\{.*?)(\\[.*?\\])(.*?\\})";
        String json = "{ \"list\" : [\"123\"] }";
        Matcher matcher = Pattern.compile(regx).matcher(json);
        if (matcher.find()) {
            System.out.println(matcher.group(2));
        }
        //        System.out.println(Pattern.matches(regx, json));
    }
}
