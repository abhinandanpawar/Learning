package com.example;

import java.util.List;

/**
 * Main application to demonstrate modern Java features.
 */
public class ModernJavaDemo {

    // --- 1. Records (Java 16) ---
    // A record is a concise, immutable data class.
    // The compiler generates the constructor, getters, equals, hashCode, and toString.
    public record User(String id, String name) {}

    // --- 2. Sealed Interfaces (Java 17) ---
    // This interface can only be implemented by the classes listed in 'permits'.
    public sealed interface Shape permits Circle, Square {}
    public record Circle(double radius) implements Shape {}
    public record Square(double side) implements Shape {}


    public static void main(String[] args) {
        System.out.println("--- Modern Java Features Showcase ---");

        // --- `var` for local variables (Java 11) ---
        System.out.println("\n1. Using 'var' for type inference:");
        var userList = List.of(
            new User("1", "Alice"),
            new User("2", "Bob")
        );
        System.out.println("   Inferred type is: " + userList.getClass().getName());
        System.out.println("   Content: " + userList);


        // --- Records in action ---
        System.out.println("\n2. Using records:");
        var user = new User("3", "Charlie");
        System.out.println("   Record object: " + user);
        System.out.println("   Accessor method: " + user.name()); // e.g., user.name() instead of user.getName()


        // --- Switch Expressions and Pattern Matching (Java 17) ---
        System.out.println("\n3. Using switch expressions and pattern matching:");
        Shape shape = new Circle(10.0);
        double area = getArea(shape);
        System.out.println("   The area of a " + shape + " is: " + area);

        Shape shape2 = new Square(5.0);
        double area2 = getArea(shape2);
        System.out.println("   The area of a " + shape2 + " is: " + area2);
    }

    /**
     * This method uses a modern switch expression with pattern matching.
     * It's more concise and safer than traditional switch statements.
     */
    public static double getArea(Shape shape) {
        return switch (shape) {
            // 'case Circle c' is the pattern match. 'c' is automatically cast.
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Square s -> s.side() * s.side();
        };
    }
}
