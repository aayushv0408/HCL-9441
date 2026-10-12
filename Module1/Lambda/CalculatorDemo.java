package Lambda;

// Functional Interface
@FunctionalInterface
interface Calculator {
    double calculate(double a, double b);
}

@FunctionalInterface
interface NumberCheck {
    void check(int n);
}

public class CalculatorDemo {

    public static void main(String[] args) {

        Calculator add = (a, b) -> a + b;
        System.out.println("Addition: " + add.calculate(20, 10));

        Calculator subtract = (a, b) -> a - b;
        System.out.println("Subtraction: " + subtract.calculate(20, 10));

        Calculator multiply = (a, b) -> a * b;
        System.out.println("Multiplication: " + multiply.calculate(20, 10));

        Calculator divide = (a, b) -> a / b;
        System.out.println("Division: " + divide.calculate(20, 10));

        Calculator modulus = (a, b) -> a % b;
        System.out.println("Modulus: " + modulus.calculate(20, 10));

        NumberCheck obj = n -> {
            if (n % 2 == 0) {
                System.out.println(n + " is Even");
            } else {
                System.out.println(n + " is Odd");
            }
        };

        obj.check(10);
        obj.check(7);

    }
}
