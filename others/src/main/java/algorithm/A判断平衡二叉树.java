package main.java.algorithm;

import java.util.Stack;

/**
 * @Auth justinniu
 * @Date 2018/9/26
 * @Desc
 */
public class A判断平衡二叉树 {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    public static boolean  IsBalanced_Solution(TreeNode root) {
        return getDepth(root) != -1;
    }
    public static int getDepth(TreeNode root) {
        if (root == null) return 0;
        int left = getDepth(root.left);
        if (left == -1) return -1;
        int right = getDepth(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }

    public static void xiandi(TreeNode n) {
        if (n != null) {
            xiandi(n.left);
            xiandi(n.right);
            System.out.println(n.val);
        }
    }

    public static void xianfei(TreeNode n) {
        Stack<TreeNode> s = new Stack();
        while (n != null || !s.empty()) {
            while (n != null) {
                s.push(n);
                System.out.println(s.peek().val);
                n = n.left;
            }
            if (!s.empty()) {
                n = s.pop();
                n = n.right;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        TreeNode f = new TreeNode(6);
        TreeNode g = new TreeNode(7);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        IsBalanced_Solution(a);
    }
}

