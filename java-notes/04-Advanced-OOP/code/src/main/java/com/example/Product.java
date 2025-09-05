package com.example;

/**
 * An abstract base class for all products.
 * It cannot be instantiated directly. It provides shared state and behavior
 * for all subclasses, defining an "is-a" relationship (e.g., a Book is a Product).
 */
public abstract class Product {

    // Common fields for all products
    private String name;
    private double price;
    private ProductCategory category;

    /**
     * Constructor for the abstract class.
     * Subclasses will call this using 'super()'.
     */
    public Product(String name, double price, ProductCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // Common methods (concrete implementations)
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    /**
     * An abstract method.
     * There is no implementation here. Subclasses are forced to provide
     * their own implementation of this method.
     */
    public abstract void displayDetails();
}
