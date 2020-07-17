package main.java.algorithm;

import java.util.Stack;

/**
 * @Auth justinniu
 * @Date 2018/9/14
 * @Desc
 */
public class A0914 {
    public static void main(String[] args) {
        Stack<Integer> a = new Stack<>();
        Stack<Integer> b = new Stack<>();
        a.push(3);

        a.push(7);
        a.push(12);
        a.push(1);
        a.push(9);
        a.push(10);
        while (!a.empty()) {
            if (b.empty() || a.peek() <= b.peek()) {
                b.push(a.pop());
            }
            else {
                int temp = a.pop();
                while (!b.empty()) {
                    a.push(b.pop());
                }
                b.push(temp);
            }
        }
        b.stream().forEach(System.out::println);
        //因为c++pop()没有返回值，不能做到把最大值保存下来，又用在存回原数组，一个变量不能够实现
    }
}
