
package Generics;

public class GenericDemo {
    public static void main(String[] args) {

        // Integer type
        GenericBox<Integer> b1 = new GenericBox<>();
        b1.display(100);
        b1.show(500);

        // String type 
        
        GenericBox<String> b2 = new GenericBox<>();
        b2.display("Hello Java");
        b2.show("Generics");

        // Double type
        GenericBox<Double> b3 = new GenericBox<>();
        b3.display(85.5);
    }
}
