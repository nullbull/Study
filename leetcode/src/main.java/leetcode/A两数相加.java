package main.java.leetcode;

public class A两数相加 {


    public static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
     }

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            ListNode first = new ListNode(0);
            ListNode ans = first;

            int value = 0;
            int ten = 1;
            int carry = 0;

            while (l1 != null && l2 != null) {
                int sum =  l1.val + l2.val + carry;
                ans.next = new ListNode(sum % 10);
                carry = sum / 10;
                ans = ans.next;
                l1 = l1.next;
                l2 = l2.next;
            }
            while (l1 != null) {
                int sum =  l1.val  + carry;
                ans.next = new ListNode(sum % 10);
                carry = sum / 10;
                ans = ans.next;
                l1 = l1.next;
            }

            while (l2 != null) {
                int sum =  l2.val  + carry;
                ans.next = new ListNode(sum % 10);
                carry = sum / 10;
                ans = ans.next;
                l2 = l2.next;

            }
            if (carry != 0) {
                ans.next = new ListNode(carry);
            }


//            ArrayList<Integer> a = new ArrayList();
//            ArrayList<Integer> b = new ArrayList();
//
//            while(l1 != null) {
//                a.add(l1.val);
//                l1 = l1.next;
//            }
//            while (l2 != null) {
//                b.add(l2.val);
//                l2 = l2.next;
//            }
//
////            Collections.reverse(a);
////            Collections.reverse(b);
//
//            int lengthA = a.size();
//            int lengthB = b.size();
//
//            int min = Math.min(lengthA, lengthB);
//            int max = Math.max(lengthA, lengthB);
//            ArrayList<Integer> big;
//            if (lengthA >= lengthB) {
//                big = a;
//            } else {
//                big = b;
//            }
//
//            int value = 0;
//            int ten = 1;
//            int carry = 0;
//            for (int i = 0; i < min; i++) {
//                int sum = a.get(i) + b.get(i) + carry;
//                value = value + ten * (sum % 10);
//                carry = sum / 10;
//                ten = ten * 10;
//            }
//            if (min != max) {
//                for (int i = min; i < max; i++) {
//                    int sum = big.get(i) + carry;
//                    value = value + ten * (sum % 10);
//                    carry = sum / 10;
//                    ten = ten * 10;
//                }
//            }
//            if (carry != 0) {
//                value = value + carry * ten;
//            }
//            ListNode first = new ListNode(0);
//            ListNode ans = first;
//            while (value != 0) {
//                first.next = new ListNode(value % 10);
//                first = first.next;
//                value = value / 10;
//            }
            return first.next;
        }
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(2);
        ListNode aF = a;
        a.next = new ListNode(4);
        a = a.next;
        a.next = new ListNode(3);

        ListNode b = new ListNode(5);
        ListNode bF = b;
        b.next = new ListNode(6);
        b = b.next;
        b.next = new ListNode(4);
//        b = b.next;
//        b.next = new ListNode(1);

        ListNode listNode = new Solution().addTwoNumbers(aF, bF);
    }

}
