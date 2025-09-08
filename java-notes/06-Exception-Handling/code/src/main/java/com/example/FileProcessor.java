package com.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main application to demonstrate exception handling concepts.
 */
public class FileProcessor {

    public static void main(String[] args) {
        System.out.println("--- Exception Handling Showcase ---");

        // Your Mission:
        // The 'processFile' method can throw two checked exceptions:
        // IOException and InvalidFileException.
        // 1. Wrap the call to processFile("sample.txt") in a 'try' block.
        // 2. Add a 'catch' block for InvalidFileException and print its message to System.err.
        // 3. Add a 'catch' block for the more general IOException and print its message.
        // 4. Run the code with and without the 'sample.txt' file to see both handlers work.

        // --- Your code goes here ---
        // processFile("sample.txt");
        // System.out.println("File processing completed successfully.");
        // --- End of your code ---

        System.out.println("-----------------------------------");
    }

    /**
     * Reads a file and checks its content.
     * @param filePath The path to the file.
     * @throws IOException If there's an error reading the file.
     * @throws InvalidFileException If the file content is invalid.
     */
    public static void processFile(String filePath) throws IOException, InvalidFileException {
        // 1. Using try-with-resources - the best way to handle resources.
        // The BufferedReader will be closed automatically, even if exceptions occur.
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String firstLine = reader.readLine();

            if (firstLine == null) {
                // 2. Throwing our custom exception
                throw new InvalidFileException("File is empty.");
            }

            System.out.println("First line of file: '" + firstLine + "'");

            if (firstLine.equalsIgnoreCase("fail")) {
                // 3. Throwing an unchecked exception to simulate a bug
                throw new NullPointerException("Simulating a bug during processing!");
            }

        } catch (FileNotFoundException e) {
            // 4. Catching a specific, checked exception.
            // We can log it and then re-throw it as our custom exception
            // to provide more context to the caller (Exception Chaining).
            throw new InvalidFileException("Input file not found at path: " + filePath);
        }
    }
}
