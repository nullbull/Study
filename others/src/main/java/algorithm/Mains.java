package algorithm;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Auth justinniu
 * @Date 2018/9/21
 * @Desc
 */

public class Mains {
    static class LRU<V, T> extends LinkedHashMap {
        private int size;
        public LRU(int size) {
            super(size, (float) 0.75, true);
            this.size = size;
        }
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            if (super.size() > size) {
                return true;
            }
            return false;
        }


        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            LRU lru = new LRU(n);
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
                    if (lru.containsKey(Integer.valueOf(a[0]))) {
                        System.out.println(lru.get(Integer.valueOf(a[0])));
                    } else {
                        System.out.println("-1");
                    }
                    break;
                }
            }
        }
    }

}
