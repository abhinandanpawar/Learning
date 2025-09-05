package com.example;

/**
 * Represents a Book, which is a specific type of Product.
 * This is a subclass of Product and demonstrates inheritance.
 */
public class Book extends Product {
    // Additional state specific to a Book
    private String author;

    /**
     * Constructor for a Book object.
     * @param name The name of the book.
     * @param price The price of the book.
     * @param author The author of the book.
     */
    public Book(String name, double price, String author) {
        // 'super()' calls the constructor of the parent class (Product).
        // This must be the first line in a constructor.
        super(name, price);
        this.author = author;
    }

    // Getter for the new field
    public String getAuthor() {
        return author;
    }

    /**
     * Overriding the display method from the Product class.
     * The @Override annotation is not required, but it's good practice
     * as it tells the compiler you intend to override a method.
     * If you make a mistake (e.g., wrong method name), the compiler will error.
     */
    @Override
    public void display() {
        // We can call the parent's method using 'super'
        super.display();
        // And then add the subclass-specific information
        System.out.println("Author: " + author);
    }
}
