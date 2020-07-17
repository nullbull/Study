package main.java.algorithm;

/**
 * @Auth justinniu
 * @Date 2018/10/14
 * @Desc
 */
public class a10142 {

    private class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode Merge(ListNode p1, ListNode p2) {
        if (null == p1) {
            return p2;
        }
        if (null == p2) {
            return p1;
        }
        ListNode p3 = null;
        ListNode temp = null;
        if (p1.val >= p2.val) {
            p3 = p2;
            temp = p3;
            p2 = p2.next;
        } else {
            p3 = p1;
            temp = p3;
            p1 = p1.next;
        }
        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                temp.next = p2;
                temp = temp.next;
                p2 = p2.next;
            } else {
                temp.next = p1;
                temp = temp.next;
                p1 = p1.next;
            }
        }
        if (p1 == null)
            temp.next = p2;
        if (p2 == null)
            temp.next = p1;
        return p3;
    }
}

