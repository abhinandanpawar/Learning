package com.example;

/**
 * A concrete class representing a Book.
 * It extends the abstract Product class and implements the Shippable interface.
 */
public class Book extends Product implements Shippable {

    private double weight; // in kilograms

    public Book(String name, double price, double weight) {
        // Call the constructor of the parent abstract class
        super(name, price, ProductCategory.BOOKS);
        this.weight = weight;
    }

    /**
     * This is the implementation of the abstract method from the Product class.
     * Every concrete subclass must provide this.
     */
    @Override
    public void displayDetails() {
        System.out.println("--- Book ---");
        System.out.println("Name: " + getName());
        System.out.println("Price: $" + getPrice());
        System.out.println("Category: " + getCategory());
        System.out.println("Weight: " + weight + " kg");
    }

    /**
     * This is the implementation of the method from the Shippable interface.
     */
    @Override
    public double calculateShippingCost() {
        // A simple calculation: $2.50 per kilogram
        return this.weight * 2.50;
    }
}
