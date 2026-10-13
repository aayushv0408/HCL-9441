package Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamDemo {
    public static void main(String[] args) {

        // Integer ArrayList
        ArrayList<Integer> l1 = new ArrayList<>(
            Arrays.asList(10, 15, 20, 25, 30, 35, 40)
        );

        // String ArrayList


        
        ArrayList<String> l2 = new ArrayList<>(
            Arrays.asList("Aayush", "Aman", "Rahul",
                          "Ankit", "Arjun", "Priya")
        );

        // Filter even numbers using Stream and Lambda
        System.out.println("Even Numbers:");
        l1.stream()
          .filter(n -> n % 2 == 0)
          .forEach(n -> System.out.println(n));

        // Filter numbers greater than 20
        System.out.println("Numbers greater than 20:");
        l1.stream()
          .filter(n -> n > 20)
          .forEach(n -> System.out.println(n));

        // Filter names starting with A
        System.out.println("Names starting with A:");
        l2.stream()
          .filter(name -> name.startsWith("A"))
          .forEach(name -> System.out.println(name));
    }
}