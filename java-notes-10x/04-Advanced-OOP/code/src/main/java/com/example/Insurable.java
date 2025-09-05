package com.example;

/**
 * An interface for items that can be insured during shipping.
 * A class can implement multiple interfaces, so a valuable item
 * could be both Shippable and Insurable.
 */
public interface Insurable {

    /**
     * Gets the value of the item for insurance purposes.
     * @return the value of the item.
     */
    double getInsuranceValue();
}
