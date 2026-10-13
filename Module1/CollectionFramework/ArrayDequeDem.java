package CollectionFramework;

import java.util.ArrayDeque;

public class ArrayDequeD {

    public static void main(String[] args) {

        // Creating ArrayDeque
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // 1. add() - element add at the end
        deque.add(10);
        deque.add(20);
        deque.add(30);

        System.out.println("Deque: " + deque);

        // 2. addFirst() - add at front
        deque.addFirst(5);

        System.out.println("After addFirst(): " + deque);

        // 3. addLast() - add at end
        deque.addLast(40);

        System.out.println("After addLast(): " + deque);

        // 4. offerFirst()
        deque.offerFirst(1);

        System.out.println("After offerFirst(): " + deque);

        // 5. offerLast()
        deque.offerLast(50);

        System.out.println("After offerLast(): " + deque);

        // 6. getFirst()
        System.out.println("First element: " + deque.getFirst());

        // 7. getLast()
        System.out.println("Last element: " + deque.getLast());

        // 8. peekFirst()
        System.out.println("Peek First: " + deque.peekFirst());

        // 9. peekLast()
        System.out.println("Peek Last: " + deque.peekLast());

        // 10. removeFirst()
        System.out.println("Removed First: " + deque.removeFirst());

        System.out.println("After removeFirst(): " + deque);

        // 11. removeLast()
        System.out.println("Removed Last: " + deque.removeLast());

        System.out.println("After removeLast(): " + deque);

        // 12. pollFirst()
        System.out.println("Poll First: " + deque.pollFirst());

        System.out.println("After pollFirst(): " + deque);

        // 13. pollLast()
        System.out.println("Poll Last: " + deque.pollLast());

        System.out.println("After pollLast(): " + deque);

        // 14. push() - add at front
        deque.push(100);

        System.out.println("After push(): " + deque);

        // 15. pop() - remove from front
        System.out.println("Pop: " + deque.pop());

        System.out.println("After pop(): " + deque);

        // 16. contains()
        System.out.println("Contains 20: " + deque.contains(20));

        // 17. size()
        System.out.println("Size: " + deque.size());

        // 18. isEmpty()
        System.out.println("Is Empty: " + deque.isEmpty());

        // 19. clear()
        deque.clear();

        System.out.println("After clear(): " + deque);

        System.out.println("Is Empty: " + deque.isEmpty());
    }
}