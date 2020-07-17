package algorithm;

import java.util.Stack;

/**
 * @Auth justinniu
 * @Date 2018/10/16
 * @Desc
 */
public class A包含min的栈 {
    public Stack<Integer> data = new Stack<>();
    public Stack<Integer> min = new Stack<>();
    public void push(int node) {
        if (!min.isEmpty()) {
            if (min.peek() > node) {
                min.push(node);
            }
            data.push(node);
        }else {
            min.push(node);
            data.push(node);
        }
    }

    public void pop() {
       if (data.pop().equals(min.peek())) {
           min.pop();
       }
    }

    public int top() {
        return data.peek();
    }

    public int min() {
        return min.peek();
    }
}
