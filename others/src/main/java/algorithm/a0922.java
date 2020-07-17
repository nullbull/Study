package main.java.algorithm;

/**
 * @Auth justinniu
 * @Date 2018/9/22
 * @Desc
 */
public class a0922 {
//    public static String s = new String("123");
//    {
//        s = "yangxu" ;
//    }
//    static {
//        System.out.println("static system. out");
//    }
//    public a0922() {
//        System.out.println("Constxxxxxxxx");
//    }
//    public static void main(String[] args) {
//        System.out.println(new  a0922().s);
//    }
static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) {
              val = x;
              next = null;
          }
      }

    static ListNode  insertSortList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode cur = head;
            ListNode helper = new ListNode(0);
            ListNode pre = null;
            while (cur != null) {
                ListNode next = cur.next;
                pre = helper;
                while (pre.next != null && pre.next.val < cur.val) {
                    pre = pre.next;
                }
                cur.next = pre.next;
                pre.next = cur;
                cur = next;
            }
            return helper.next;
         }
    public static void main(String[] args) {
        ListNode a = new ListNode(6);
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(11);
        ListNode d = new ListNode(20);
        ListNode e = new ListNode(1);
        ListNode f = new ListNode(99);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        a = insertSortList(a);
        while (a!=null) {
            System.out.println(a.val);
            a = a.next;
        }
    }


}
