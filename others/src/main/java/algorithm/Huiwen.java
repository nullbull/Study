package main.java.algorithm;

/**
 * @Auth justinniu
 * @Date 2018/9/25
 * @Desc
 */
public class Huiwen {
    class ListNode {
        ListNode next;
        int val;
        ListNode(int val) {
            this.val = val;
        }
    }

    public boolean judge(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode mid = slow.next;
        slow.next = null;

        ListNode pre = null;
        while (mid != null) {
            ListNode next = mid.next;
            mid.next = pre;
            pre = mid;
            mid = next;
        }
        while (head != null && pre != null) {
            if (head.val != pre.val)
                return false;
            head = head.next;
            pre = pre.next;
        }
        return true;
    }

}
