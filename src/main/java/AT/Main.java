package AT;

import java.util.*;

/**
 * @Auth justinniu
 * @Date 2018/9/28
 * @Desc
 */
public class Main {
   static class LRUCache {
        Queue<Integer> q = new LinkedList<>();
        HashMap<Integer, Integer> map = new LinkedHashMap();
        int size;
        int length = 0;
        public LRUCache(int capacity) {
            this.size = capacity;
        }

        public int get(int key) {
            if (map.containsKey(key)) return map.get(key);
            return -1;
        }

        public void put(int key, int value) {
            q.offer(key);
            length++;
            map.put(key, value);
            if (length > size) {
                map.remove(q.poll());
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LRUCache lru = new LRUCache(n);
        sc.nextLine();
        while (sc.hasNext()) {
            String s = sc.nextLine();
            //  if (s.equals("")) break;
            String a[] = s.split(" ");
            if (a.length == 2) {
                int k = Integer.valueOf(a[0]);
                int v = Integer.valueOf(a[1]);
                lru.put(k, v);
            }
            if (a.length == 1) {
                System.out.println( lru.get(Integer.valueOf(a[0])));
                break;
            }
        }
    }
}
