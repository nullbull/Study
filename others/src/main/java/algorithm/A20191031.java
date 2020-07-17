package algorithm;

import java.util.Vector;

/**
 * @author: niuzhenhao
 * @date: 2019-10-31 11:01
 * @desc:
 */
public class A20191031 {

    static class NestedInteger {
        private Object obj;
        public NestedInteger(Object o) {
            this.obj = o;
        }
        public boolean isInteger(){
            return obj instanceof Integer;
        };
        public int getInteger(){
            return (int)obj;
        };
        public Vector<NestedInteger> getList(){
            return (Vector<NestedInteger>) obj;
        }
        ;
    };


    static class NestedIterator {
        private Vector<NestedInteger> nestedList;
        volatile int current = 0;
        private int length;
        private NestedInteger temp = null;
        public NestedIterator(Vector<NestedInteger> nestedList) {
            this.nestedList = nestedList;
            this.length = nestedList.size();
            this.temp = nestedList.get(current);
        }
        public int next() {
            if (current < length) {
                NestedInteger nestedInteger = nestedList.get(current++);
                temp = current < length ? nestedList.get(current) : null;
                if (nestedInteger.isInteger()) {
                    return nestedInteger.getInteger();
                } else {
                    NestedIterator nestedIterator = new NestedIterator(nestedInteger.getList());
                    while (nestedIterator.hasNext()) {
                        System.out.print(nestedIterator.next());
                    }
                }
            } else {
                temp = null;
            }
            return -1;
        }

        public boolean hasNext() {
            return temp != null && current < length;
        }
    };

    public static void main(String[] args) {
        Vector<NestedInteger> list = new Vector<>();
        list.add(new NestedInteger(1));
        list.add(new NestedInteger(1));
        Vector<NestedInteger> vector = new Vector<>();
        NestedInteger a = new NestedInteger(list);
        NestedInteger b = new NestedInteger(2);
        NestedInteger c = new NestedInteger(list);
        vector.add(a);
        vector.add(b);
        vector.add(c);
        Vector<NestedInteger> d = new Vector<>();
        d.add(new NestedInteger(6));
        Vector<NestedInteger> e = new Vector<>();
        e.add(new NestedInteger(4));
        NestedInteger f = new NestedInteger(e);
        d.add(f);
        NestedInteger g = new NestedInteger(d);
        vector.add(g);
        NestedIterator it =  new NestedIterator(vector);
        while (it.hasNext()) {
            System.out.print(it.next());
        }
    }
}
