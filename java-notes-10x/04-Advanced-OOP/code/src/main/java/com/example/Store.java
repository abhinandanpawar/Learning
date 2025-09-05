package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * The main application class to demonstrate the advanced OOP concepts.
 */
public class Store {

    public static void main(String[] args) {
        System.out.println("--- Advanced OOP Showcase: Product Catalog ---");

        // We can create a list of Products. Note that we cannot do 'new Product()'
        // because the Product class is abstract.
        List<Product> catalog = new ArrayList<>();
        catalog.add(new Book("The Pragmatic Programmer", 45.00, 0.8));
        catalog.add(new Electronic("Laptop", 1200.00, 2.5));
        catalog.add(new Electronic("Smartphone", 800.00, 0.5));

        // --- Demonstrate Polymorphism with Abstract Classes ---
        System.out.println("\n--- Displaying Product Details ---");
        for (Product product : catalog) {
            product.displayDetails(); // Calls the correct overridden method
            System.out.println();
        }

        // --- Demonstrate Polymorphism with Interfaces ---
        System.out.println("\n--- Calculating Shipping for Shippable Items ---");
        List<Shippable> itemsToShip = new ArrayList<>();
        itemsToShip.add(new Book("Clean Code", 35.00, 0.7));
        itemsToShip.add(new Electronic("Tablet", 500.00, 0.6));

        for (Shippable item : itemsToShip) {
            // We can only see methods from the 'Shippable' interface contract
            // and the 'Object' class. We can't call 'getPrice()' on 'item'.
            double shippingCost = item.calculateShippingCost();
            System.out.println("Item shipping info: " + item.getShippingInfo());
            System.out.println("Shipping cost: $" + String.format("%.2f", shippingCost));
            System.out.println();
        }

        // --- Demonstrate Enum Usage ---
        Product firstProduct = catalog.get(0);
        if (firstProduct.getCategory() == ProductCategory.BOOKS) {
            System.out.println("\nThe first product in the catalog is a book.");
        }
    }
}
