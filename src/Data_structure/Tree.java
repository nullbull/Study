package Data_structure;


import java.util.Stack;

public class Tree {
    public int Data;
    public Tree leftNode;
    public Tree rightNode;

    public Tree getRoot() {
        return root;
    }

    private  Tree root;

    @Override
    public String toString() {
        return String.valueOf(Data);
    }

    public void insert(int data) {
        Tree newNode = new Tree();
        newNode.Data = data;
        newNode.leftNode = null;
        newNode.rightNode = null;
        if (root == null) {
            root = newNode;
        }
        else {
            Tree current = root;
            Tree parent;
            while (true) {
                parent = current;
                if (data < current.Data) {
                    current = parent.leftNode;
                    if (current == null)
                    {
                        parent.leftNode = newNode;
                        return;
                    }
                }
                else {
                    current = parent.rightNode;
                    if (current == null)
                    {
                        parent.rightNode = newNode;
                        return;
                    }

                }
            }
        }
    }
    public  static void visitTree(Tree tree) {
        Stack<Tree> vt = new Stack<>();
        Tree p = tree.getRoot();
        vt.push(p);
        while (p != null && ! vt.empty()) {
            while (p != null) {
                System.out.println(p);
                vt.push(p);
                p = p.leftNode;
            }
            while (!vt.empty()) {
                p = vt.pop();
                p = p.rightNode;
            }
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        visitTree(tree);
    }
}
