package main.java.Object;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LinkedListFeatures {
    public static void main(String[] args){
        List<String> list = Arrays.asList("1", "2", "3", "4");
        LinkedList linkedList = new LinkedList(list);
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.element());
        System.out.println(linkedList.remove());
        System.out.println(linkedList.peek());
        System.out.println(linkedList.removeFirst());
        System.out.println(linkedList.poll());
        linkedList.addFirst("zz");
        linkedList.addLast("hhe");
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.removeLast());

    }
}
