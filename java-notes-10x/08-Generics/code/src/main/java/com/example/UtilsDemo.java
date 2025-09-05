package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Main application to demonstrate various Generics concepts.
 */
public class UtilsDemo {

    // --- A Generic Method ---
    /**
     * A generic method to print the contents of any list.
     * The '<T>' before the return type declares this as a generic method.
     */
    public static <T> void printList(List<T> list) {
        System.out.print("[ ");
        for (T element : list) {
            System.out.print(element + " ");
        }
        System.out.println("]");
    }

    // --- Bounded Wildcards and PECS ---
    /**
     * Copies elements from a source list to a destination list.
     * This method is a perfect example of the PECS principle.
     *
     * @param src  The source list to copy from (Producer).
     * @param dest The destination list to copy to (Consumer).
     * @param <T>  The type of elements.
     */
    public static <T> void copy(List<? extends T> src, List<? super T> dest) {
        // 'src' is a PRODUCER, so we use 'extends'. We can get T's from it.
        // 'dest' is a CONSUMER, so we use 'super'. We can put T's into it.
        for (T element : src) {
            dest.add(element);
        }
    }


    public static void main(String[] args) {
        System.out.println("--- Generics Showcase ---");

        // 1. Using our generic Box<T> class
        System.out.println("\n1. Demonstrating Generic Box<T> class:");
        Box<Integer> integerBox = new Box<>();
        integerBox.put(123);
        System.out.println(integerBox);

        Box<String> stringBox = new Box<>();
        stringBox.put("Generic String");
        System.out.println(stringBox);


        // 2. Using our generic printList<T> method
        System.out.println("\n2. Demonstrating Generic printList<T> method:");
        List<Double> doubles = List.of(1.1, 2.2, 3.3);
        List<String> names = List.of("Alice", "Bob", "Charlie");
        System.out.print("Doubles: ");
        printList(doubles);
        System.out.print("Names: ");
        printList(names);


        // 3. Demonstrating PECS with the copy() method
        System.out.println("\n3. Demonstrating PECS with copy():");

        // We have a list of Integers (which are Numbers)
        List<Integer> integers = List.of(1, 2, 3);

        // We want to copy them into a list of Numbers.
        List<Number> numbers = new ArrayList<>();

        // This works because List<Integer> is a subtype of List<? extends Number> (Producer)
        // And List<Number> is a subtype of List<? super Integer> (Consumer)
        copy(integers, numbers);

        System.out.print("Source Integers: ");
        printList(integers);
        System.out.print("Copied Numbers: ");
        printList(numbers);
    }
}
