
package CollectionFramework;

import java.util.PriorityQueue;

public class PriorityQueueD {

    public static void main(String[] args) {

        // 1. Create PriorityQueue
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 2. add() - insert elements
        pq.add(40);
        pq.add(10);
        pq.add(30);
        pq.add(20);
        pq.add(50);

        System.out.println("PriorityQueue: " + pq);

        // 3. offer() - insert element
        pq.offer(5);
        System.out.println("After offer(): " + pq);

        // 4. peek() - view highest-priority element
        System.out.println("Peek: " + pq.peek());

        // 5. element() - view head element
        System.out.println("Element: " + pq.element());

        // 6. poll() - remove and return head
        System.out.println("Poll: " + pq.poll());
        System.out.println("After poll(): " + pq);

        // 7. remove() - remove head
        System.out.println("Removed: " + pq.remove());
        System.out.println("After remove(): " + pq);

        // 8. contains()
        System.out.println("Contains 30: " + pq.contains(30));
        System.out.println("Contains 100: " + pq.contains(100));

        // 9. size()
        System.out.println("Size: " + pq.size());

        // 10. isEmpty()
        System.out.println("Is Empty: " + pq.isEmpty());

        // 11. Iterate through PriorityQueue
        System.out.println("Remaining elements:");
        for (int x : pq) {
            System.out.println(x);
        }

        // 12. Remove a specific element
        pq.remove(Integer.valueOf(30));
        System.out.println("After removing 30: " + pq);

        // 13. Create another PriorityQueue
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        pq2.add(60);
        pq2.add(70);

        // 14. addAll()
        pq.addAll(pq2);
        System.out.println("After addAll(): " + pq);

        // 15. clear()
        pq.clear();
        System.out.println("After clear(): " + pq);

        // 16. isEmpty() after clear
        System.out.println("Is Empty now: " + pq.isEmpty());
    }
}
