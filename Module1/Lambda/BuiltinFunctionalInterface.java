package Lambda;

import java.util.function.*;

public class BuiltinFunctionalInterface {
    public static void main(String[] args) {

        Predicate<Integer> evenOdd = n -> n % 2 == 0;

        int num = 10;

        if (evenOdd.test(num)) {
            System.out.println(num + " is Even");
        } else {
            System.out.println(num + " is Odd");
        }

        Function<Integer, Integer> square = n -> n * n;

        System.out.println("Square: " + square.apply(5));

        Supplier<String> message = () -> "Hello Java";

        System.out.println("Supplier: " + message.get());

        Consumer<Integer> display = n ->
            System.out.println("Consumer value: " + n);

        display.accept(100);
    }
}