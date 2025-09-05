package com.example;

/**
 * This is a custom CHECKED exception.
 * We extend 'Exception' to create a checked exception.
 * This is for application-specific errors that the calling code should
 * be forced to handle.
 */
public class InvalidFileException extends Exception {

    /**
     * Constructor that takes a message.
     * @param message A description of the error condition.
     */
    public InvalidFileException(String message) {
        // Pass the message up to the parent Exception class.
        super(message);
    }
}
