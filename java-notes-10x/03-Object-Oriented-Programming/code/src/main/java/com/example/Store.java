package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class for our OOP showcase application.
 * This class demonstrates polymorphism in action.
 */
public class Store {
    public static void main(String[] args) {
        System.out.println("Welcome to the 10X Java OOP Showcase!");

        // Here we demonstrate polymorphism.
        // We create a list that holds 'Product' objects.
        List<Product> products = new ArrayList<>();

        // We can add a generic Product object.
        products.add(new Product("Laptop", 1200.00));

        // We can also add a Book object, because a Book IS A Product.
        products.add(new Book("The Pragmatic Programmer", 45.00, "Andy Hunt"));

        // Let's create a new Clothing class as suggested in the exercises
        // to further demonstrate the power of this concept.
        Clothing shirt = new Clothing("T-Shirt", 25.00, "M", "Blue");
        products.add(shirt);


        System.out.println("\n--- Displaying all products in our store ---");
        // Now, we loop through the list and call the display() method on each item.
        // The JVM will automatically call the correct version of display()
        // for each object (the Product, Book, or Clothing version).
        // This is dynamic method dispatch at work!
        for (Product product : products) {
            product.display();
        }
        System.out.println("-------------------------");

    }
}


/**
 * A Clothing class as described in the exercises.
 * This shows how easy it is to extend the system.
 */
class Clothing extends Product {
    private String size;
    private String color;

    public Clothing(String name, double price, String size, String color) {
        super(name, price);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Size: " + size);
        System.out.println("Color: " + color);
    }
}
