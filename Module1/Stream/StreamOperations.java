package Stream;

import java.util.*;
import java.util.stream.*;

public class StreamOperations {
    public static void main(String[] args) {

        ArrayList<String> l1 = new ArrayList<>(
            Arrays.asList(
                "Aayush", "Aman", "Rahul", "Ankit",
                "Aman", "Priya", "Arjun", "Rohit"
            )
        );

        System.out.println("Original List: " + l1);

        // 1. forEach() - Print all elements
        System.out.println("\n1. forEach:");
        l1.stream().forEach(System.out::println);

        // 2. filter() - Names starting with A
        System.out.println("\n2. filter:");
        l1.stream()
          .filter(name -> name.startsWith("A"))
          .forEach(System.out::println);

        // 3. count() - Count total elements
        System.out.println("\n3. count:");
        System.out.println(l1.stream().count());

        // 4. findFirst() - Find first element
        System.out.println("\n4. findFirst:");
        System.out.println(l1.stream().findFirst().get());

        // 5. findAny() - Find any element
        System.out.println("\n5. findAny:");
        System.out.println(l1.stream().findAny().get());

        // 6. sorted() - Sort alphabetically
        System.out.println("\n6. sorted:");
        l1.stream().sorted().forEach(System.out::println);

        // 7. distinct() - Remove duplicates
        System.out.println("\n7. distinct:");
        l1.stream().distinct().forEach(System.out::println);

        // 8. map() - Convert names to uppercase
        System.out.println("\n8. map:");
        l1.stream()
          .map(name -> name.toUpperCase())
          .forEach(System.out::println);

        // 9. anyMatch() - Any name starts with A?
        System.out.println("\n9. anyMatch:");
        System.out.println(
            l1.stream().anyMatch(name -> name.startsWith("A"))
        );

        // 10. allMatch() - All names start with A?
        System.out.println("\n10. allMatch:");
        System.out.println(
            l1.stream().allMatch(name -> name.startsWith("A"))
        );

        // 11. noneMatch() - No name starts with Z?
        System.out.println("\n11. noneMatch:");
        System.out.println(
            l1.stream().noneMatch(name -> name.startsWith("Z"))
        );

        // 12. limit() - First three elements
        System.out.println("\n12. limit:");
        l1.stream().limit(3).forEach(System.out::println);

        // 13. skip() - Skip first three elements
        System.out.println("\n13. skip:");
        l1.stream().skip(3).forEach(System.out::println);

        // 14. reduce() - Combine all names
        System.out.println("\n14. reduce:");
        String result = l1.stream()
            .reduce("", (a, b) ->
                a.isEmpty() ? b : a + ", " + b
            );

        System.out.println(result);

        // 15. filter() + count()
        System.out.println("\n15. Count names starting with A:");
        long total = l1.stream()
            .filter(name -> name.startsWith("A"))
            .count();

        System.out.println(total);

        // 16. peek() - Observe elements in a pipeline
        System.out.println("\n16. peek:");
        l1.stream()
          .filter(name -> name.length() > 4)
          .peek(name -> System.out.println("Filtered: " + name))
          .collect(Collectors.toList());
    }
}