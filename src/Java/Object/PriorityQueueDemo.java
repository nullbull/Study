package Java.Object;

import java.util.*;

public class PriorityQueueDemo {
    public static void printQ(Queue queue){
        while (queue.peek() != null)
            System.out.print(queue.remove()+ " ");
        System.out.println();
    }
    public static void main(String args[]){
     PriorityQueue priorityQueue =   new PriorityQueue<Integer>();
        Random random = new Random(47);
        for(int i = 0; i < 10; i++)
            priorityQueue.offer(random.nextInt(i + 10));
        printQ(priorityQueue);

        List<Integer> integerList = Arrays.asList(2,321, 3213, 2, 33, 44423, 23, 4, 34);

        priorityQueue = new PriorityQueue(integerList);
        printQ(priorityQueue);

        priorityQueue = new PriorityQueue(integerList.size(), Collections.reverseOrder());
        priorityQueue.addAll(integerList);
        printQ(priorityQueue);

        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        List<String> strings = Arrays.asList(fact.split(" "));
        PriorityQueue<String> stringPQueuet = new PriorityQueue<>(strings);
        printQ(stringPQueuet);

        stringPQueuet = new PriorityQueue<>(strings.size(), Collections.reverseOrder());
        printQ(stringPQueuet);

        Set<Character> charSet = new HashSet<>();
        for(char c : fact.toCharArray()){
            charSet.add(c);
        }
        PriorityQueue     <Character> charPQ = new PriorityQueue<>(charSet);
        printQ(charPQ);
    }
}
