package com.example;

/**
 * A concrete class representing an Electronic item.
 * It extends Product and implements both Shippable and Insurable,
 * demonstrating that a class can have multiple capabilities.
 */
public class Electronic extends Product implements Shippable, Insurable {

    private double weight; // in kilograms

    public Electronic(String name, double price, double weight) {
        super(name, price, ProductCategory.ELECTRONICS);
        this.weight = weight;
    }

    /**
     * Implementation of the abstract method from Product.
     */
    @Override
    public void displayDetails() {
        System.out.println("--- Electronic ---");
        System.out.println("Name: " + getName());
        System.out.println("Price: $" + getPrice());
        System.out.println("Category: " + getCategory());
        System.out.println("Weight: " + weight + " kg");
    }

    /**
     * Implementation of the method from Shippable.
     */
    @Override
    public double calculateShippingCost() {
        // Electronics are more expensive to ship
        return this.weight * 5.0;
    }

    /**
     * Implementation of the method from Insurable.
     */
    @Override
    public double getInsuranceValue() {
        // Insurance value is the price of the item.
        return getPrice();
    }

    /**
     * This class provides its own implementation for the default method
     * from the Shippable interface, overriding the default behavior.
     */
    @Override
    public String getShippingInfo() {
        return "This item ships with special handling for electronics.";
    }
}
