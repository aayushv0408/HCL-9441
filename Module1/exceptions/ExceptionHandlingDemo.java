package exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class ExceptionHandlingDemo {

    public static void main(String[] args) {
        demonstrateBasicHandling();
        demonstrateMultipleCatch();
        demonstrateNestedTry();
        demonstrateFinally();
        demonstrateThrowAndThrows();
        demonstrateCustomExceptions();
        demonstrateTryWithResources();
    }

    private static void demonstrateBasicHandling() {
        System.out.println("\n1. Basic try-catch and exception details");
        try {
            int result = 10 / 0;
            System.out.println("Result: " + result);
        } catch (ArithmeticException exception) {
            System.out.println("Caught: " + exception);
            System.out.println("Message: " + exception.getMessage());
        }
    }

    private static void demonstrateMultipleCatch() {
        System.out.println("\n2. Multiple catch blocks and multi-catch");
        String[] values = { "42", "not-a-number" };

        try {
            int index = 0;
            int number = Integer.parseInt(values[index]);
            System.out.println("Parsed number: " + number);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
            System.out.println("Invalid input or index: " + exception.getMessage());
        } catch (RuntimeException exception) {
            // Catch broader exception types after their more specific subtypes.
            System.out.println("Unexpected runtime exception: " + exception.getMessage());
        }
    }

    private static void demonstrateNestedTry() {
        System.out.println("\n3. Nested try-catch");
        try {
            try {
                int[] numbers = { 1, 2, 3 };
                System.out.println(numbers[5]);
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("Inner catch handled: " + exception.getClass().getSimpleName());
            }

            int result = 10 / 2;
            System.out.println("Outer try continues: " + result);
        } catch (ArithmeticException exception) {
            System.out.println("Outer catch handled: " + exception.getMessage());
        }
    }

    private static void demonstrateFinally() {
        System.out.println("\n4. finally block");
        try {
            throw new IllegalStateException("Example exception");
        } catch (RuntimeException exception) {
            System.out.println("Caught: " + exception.getMessage());
        } finally {
            System.out.println("finally runs after the exception is handled");
        }
    }

    private static void demonstrateThrowAndThrows() {
        System.out.println("\n5. throw, throws, and exception propagation");
        try {
            validateAge(16);
        } catch (IllegalArgumentException exception) {
            System.out.println("Validation failed: " + exception.getMessage());
        }

        try {
            readFirstLine();
        } catch (IOException exception) {
            System.out.println("Checked exception handled by caller: " + exception.getMessage());
        }
    }

    private static void validateAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be at least 18");
        }
        System.out.println("Age accepted");
    }

    private static String readFirstLine() throws IOException {
        try (BufferedReader reader = new BufferedReader(new StringReader(""))) {
            String line = reader.readLine();
            if (line == null) {
                throw new IOException("No line was available to read");
            }
            return line;
        }
    }

    private static void demonstrateCustomExceptions() {
        System.out.println("\n6. Custom checked and unchecked exceptions");
        try {
            withdraw(50, 100);
        } catch (InsufficientFundsException exception) {
            System.out.println("Checked custom exception: " + exception.getMessage());
        }

        try {
            setTemperature(-300);
        } catch (InvalidTemperatureException exception) {
            System.out.println("Unchecked custom exception: " + exception.getMessage());
        }
    }

    private static void withdraw(double balance, double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Withdrawal exceeds the available balance");
        }
        System.out.println("Withdrawal successful");
    }

    private static void setTemperature(int celsius) {
        if (celsius < -273) {
            throw new InvalidTemperatureException("Temperature is below absolute zero");
        }
        System.out.println("Temperature set to " + celsius + " C");
    }

    private static void demonstrateTryWithResources() {
        System.out.println("\n7. try-with-resources");
        try (BufferedReader reader = new BufferedReader(new StringReader("Resource closed automatically"))) {
            System.out.println(reader.readLine());
        } catch (IOException exception) {
            System.out.println("I/O error: " + exception.getMessage());
        }
    }
}

class InsufficientFundsException extends Exception {
    InsufficientFundsException(String message) {
        super(message);
    }
}

class InvalidTemperatureException extends RuntimeException {
    InvalidTemperatureException(String message) {
        super(message);
    }
}
