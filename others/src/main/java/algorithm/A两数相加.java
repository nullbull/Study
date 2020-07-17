package main.java.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auth justinniu
 * @Date 2018/9/25
 * @Desc
 */
public class A两数相加 {
      static class ListNode {
          int val;
          ListNode next;

          ListNode(int x) {
              val = x;
          }

      }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        int a[] = new int[100];
        int b[] = new int[100];
        int p1 = 0;
        int p2 = 0;
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        while (!s1.empty()) {
            a[p1++] = s1.pop();
        }
        while (!s2.empty()) {
            b[p2++] = s2.pop();
        }
        if (p1 == 1) {

        }
        if (p1 < p2) {
            int temp[] = a;
            a = b;
            b = temp;
            int t = p1;
            p1 = p2;
            p2 = t;
        }
        List<Integer> ans = new ArrayList<>();
        int temp = 0;
        for (int i = p2 - 1, j = p1 - 1; i >= 0&& j >= 0 ; i--, j--) {
            int sum = a[j] + b[i] + temp;
            if (sum >= 10) {
                ans.add(sum % 10);
                temp = sum / 10;
            } else {
                ans.add(sum);
                temp = 0;
            }
        }
        if (p1 > p2) {
            for (int i = p1-p2-1; i >= 0; i--) {
                int sum = a[i] + temp;
                if (sum >= 10) {
                    ans.add(sum % 10);
                    temp = sum / 10;
                } else {
                    ans.add(sum);
                    temp = 0;
                }
            }
        }
        if (temp > 0)
            ans.add(1);
        ListNode zwt = new ListNode(0);
        ListNode n = zwt;
        for (int i = 0; i < ans.size(); i++) {
            ListNode hhh = new ListNode(ans.get(i));
            n.next = hhh;
            n = n.next;
        }
        return zwt.next;
    }

    public static ListNode adddd(ListNode a, ListNode b) {
          ListNode zwt = new ListNode(0);
          ListNode temp = zwt;
          int extra = 0;
          while (a != null || b != null || extra != 0) {
              int sum = (a == null ? 0 : a.val) + (b == null ? 0 : b.val) + extra;
              int val = sum % 10;
              extra = sum / 10;
              temp.next= new ListNode(val);
              temp = temp.next;
              a = a == null ? a : a.next;
              b = b == null ? b : b.next;
          }
          return zwt.next;
    }

    public static void main(String[] args) {

        ListNode a = new ListNode(1);
        ListNode b = new ListNode(8);
//        ListNode c = new ListNode(9);
//        ListNode d = new ListNode(1);
        a.next = b;
//        b.next = c;
//        c.next = d;
        ListNode e = new ListNode(0);
//        ListNode f = new ListNode(7);
//        ListNode g = new ListNode(6);
//        ListNode h = new ListNode(9);
//        e.next = f;
//        f.next = g;
//        g.next = h;
//        ListNode temp = addTwoNumbers(a, e);
        ListNode temp = adddd(a, e);
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }

    }

}
