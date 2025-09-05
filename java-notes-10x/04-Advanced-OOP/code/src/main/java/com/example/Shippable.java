package com.example;

/**
 * An interface representing a contract for items that can be shipped.
 * This demonstrates the "can-do" or "has-a-capability" relationship.
 */
public interface Shippable {

    /**
     * Calculates the shipping cost.
     * @return the cost to ship the item.
     */
    double calculateShippingCost();

    /**
     * A default method, introduced in Java 8.
     * It provides a default implementation, so classes that implement this
     * interface don't have to provide their own implementation unless they want to.
     * This allows for adding new functionality to interfaces without breaking
     * existing implementing classes.
     * @return a standard shipping message.
     */
    default String getShippingInfo() {
        return "This item ships via standard ground shipping.";
    }
}
