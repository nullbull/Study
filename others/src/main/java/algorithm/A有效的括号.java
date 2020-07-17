package algorithm;

import java.util.Stack;

/**
 * @Auth justinniu
 * @Date 2018/9/26
 * @Desc
 */
public class A有效的括号 {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for (Character a : s.toCharArray()) {
            if (a == '(') stack.push(')');
            else if (a == '[') stack.push(']');
            else if (a == '{') stack.push('}');
            else if (stack.isEmpty() || a != stack.pop()) return false;
        }
        return stack.isEmpty();
    }
    public static void main(String[] args) {
        String a = "[])";
        System.out.println(isValid(a));
    }
}
