package main.java.Generic;

public class LinkedStack<T> {
    private static class Node<U> {
        U item;
        Node<U> next;
        Node() {item = null; next =null;}
        Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }
        boolean end() {return item == null && next == null;}
    }
    private Node<T> top = new Node<T>();
    public void push(T item) {
        top = new Node<T>(item, top);
    }
    public T pop() {
        T resulet = top.item;
        if(!top.end())
            top = top.next;
        return resulet;
    }
    public static void main(String[] args){
        LinkedStack<String> lss = new LinkedStack<>();
        for(String s : "Phasers on stun!".split(" "))
            lss.push(s);
        String s;
         Node<String> head = new Node<String>();
         head.next = lss.top;
        System.out.println(head.next.next.item);
        while ((s = lss.pop()) != null)
            System.out.println(s);
    }
}
