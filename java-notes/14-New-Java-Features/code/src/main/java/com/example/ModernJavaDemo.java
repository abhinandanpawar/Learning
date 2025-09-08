package com.example;

import java.util.List;
import java.util.Objects;

/**
 * Main application to demonstrate modern Java features.
 */
public class ModernJavaDemo {

    // Your Mission (Part 1):
    // This is a verbose, old-style data class.
    // 1. Refactor this 'User' class into a 'record'.
    // 2. The record should have the same fields: 'id' and 'name'.
    // 3. Delete the constructor, getters, equals, hashCode, and toString methods,
    //    as the record will generate them for you.
    public static final class User {
        private final String id;
        private final String name;

        public User(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String id() {
            return id;
        }

        public String name() {
            return name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (User) obj;
            return Objects.equals(this.id, that.id) &&
                   Objects.equals(this.name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return "User[id=" + id + ", name=" + name + ']';
        }
    }


    // This sealed interface is already modern. No changes needed here.
    public sealed interface Shape permits Circle, Square {}
    public record Circle(double radius) implements Shape {}
    public record Square(double side) implements Shape {}


    public static void main(String[] args) {
        System.out.println("--- Modern Java Features Showcase ---");

        // Using 'var' for local variables (Java 11)
        System.out.println("\n1. Using 'var' for type inference:");
        var userList = List.of(
            new User("1", "Alice"),
            new User("2", "Bob")
        );
        System.out.println("   Inferred type is: " + userList.getClass().getName());
        System.out.println("   Content: " + userList);


        // Using records in action
        System.out.println("\n2. Using records:");
        var user = new User("3", "Charlie");
        System.out.println("   Record object: " + user);
        System.out.println("   Accessor method: " + user.name());


        // Using switch expressions and pattern matching
        System.out.println("\n3. Using switch expressions and pattern matching:");
        Shape shape = new Circle(10.0);
        double area = getArea(shape);
        System.out.println("   The area of a " + shape + " is: " + area);

        Shape shape2 = new Square(5.0);
        double area2 = getArea(shape2);
        System.out.println("   The area of a " + shape2 + " is: " + area2);
    }


    // Your Mission (Part 2):
    // This method uses old-style 'instanceof' checks and casts.
    // 1. Refactor this method to use a modern 'switch' expression.
    // 2. Use pattern matching within the 'case' labels to avoid manual casting.
    public static double getArea(Shape shape) {
        if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            return Math.PI * c.radius() * c.radius();
        } else if (shape instanceof Square) {
            Square s = (Square) shape;
            return s.side() * s.side();
        } else {
            throw new IllegalArgumentException("Unknown shape");
        }
    }
}
