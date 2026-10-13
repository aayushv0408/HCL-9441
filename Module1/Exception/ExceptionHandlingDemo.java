
package Exception;

import java.io.*;
import java.sql.SQLException;

class InvalidAgeException extends Exception {
    InvalidAgeException(String message) {
        super(message);
    }
}

public class ExceptionHandlingDemo {

    // Checked exception using throws
    static void checkFile() throws IOException {
        FileReader file = new FileReader("data.txt");
        file.close();
    }

    // Custom exception using throw
    static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or above");
        }
        System.out.println("Eligible to vote");
    }

    public static void main(String[] args) {

        // 1. try-catch: ArithmeticException
        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception: " + e);
        }

        // 2. Multiple catch blocks
        try {
            int[] arr = {10, 20, 30};
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid array index");
        } catch (Exception e) {
            System.out.println("General Exception");
        }

        // 3. finally block
        try {
            System.out.println("Inside try block");
        } catch (Exception e) {
            System.out.println("Exception occurred");
        } finally {
            System.out.println("Finally block executed");
        }

        // 4. throw and custom exception
        try {
            checkAge(16);
        } catch (InvalidAgeException e) {
            System.out.println("Custom Exception: " + e.getMessage());
        }

        // 5. throws and IOException
        try {
            checkFile();
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }

        // 6. NumberFormatException
        try {
            int number = Integer.parseInt("ABC");
            System.out.println(number);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        }

        // 7. SQLException
        try {
            throw new SQLException("Database error");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

        // 8. InterruptedException
        try {
            Thread.sleep(100);
            System.out.println("Thread completed");
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
            Thread.currentThread().interrupt();
        }

        // 9. Unchecked exception
        try {
            String s = null;
            System.out.println(s.length());
        } catch (NullPointerException e) {
            System.out.println("Null value encountered");
        }

        System.out.println("Program completed");
    }
}
