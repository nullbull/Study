package util;

import java.util.regex.Pattern;

/**
 * @author 牛贞昊（niuzhenhao@58.com）
 * @date 2019/3/25 22:45
 * @desc
 */
public class URLValid {
    public static boolean isURL(String str){
        //转换为小写
        str = str.toLowerCase();
        String hh = "([/a-zA-Z0-9\\u4e00-\\u9fa5])*";
//        \&{0,1}\_{0,1}\?{0,1}\.{0,1}\={0,1}
//        String regex = "(^/([a-zA-Z0-9\\u4e00-\\u9fa5]+\\.?=?_?&?\\??-?)*)+$";
//        String regex = "^(?:\\S*\\?(?:\\w+=?[^&]+(?:&\\w+=[^&]+)*)|[-\\w.]+)$";
        String regex = "^((?:\\w+)\\??\\.?\\&?\\|?\\-?=?)+";
        return Pattern.matches( regex ,str );

    }
    public static void main(String[] args) {
        System.out.println(isURL("taobaonvz=hua??hahd9dh?hjhjhjasdlkajsd_j?2?6?1010?1?11"));
    }
}
