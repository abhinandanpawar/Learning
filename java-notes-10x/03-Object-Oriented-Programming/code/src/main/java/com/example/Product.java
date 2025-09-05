package com.example;

/**
 * Represents a generic product in our e-commerce store.
 * This is the superclass in our inheritance example.
 */
public class Product {
    // Private instance variables - this is encapsulation
    private String name;
    private double price;

    /**
     * Constructor to initialize a new Product object.
     * @param name The name of the product.
     * @param price The price of the product.
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Public getter methods (accessors) to safely expose private data
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    /**
     * A general method to display product information.
     * This method will be overridden by subclasses.
     */
    public void display() {
        System.out.println("-------------------------");
        System.out.println("Product: " + name);
        System.out.println("Price: $" + String.format("%.2f", price));
    }
}
