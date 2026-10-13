
package Generics;

interface Container<T> {
    void display(T value);
}

public class GenericBox<T> implements Container<T> {

    public void display(T value) {
        System.out.println("Value: " + value);
    }

    public <E> void show(E data) {
        System.out.println("Generic Method: " + data);
    }
}
