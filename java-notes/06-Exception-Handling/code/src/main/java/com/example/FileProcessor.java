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
        try {
            // The method we call is declared with 'throws', so we must handle it.
            processFile("sample.txt");
            System.out.println("File processing completed successfully.");
        } catch (InvalidFileException e) {
            // Handling our custom checked exception
            System.err.println("Error processing file: " + e.getMessage());
        } catch (IOException e) {
            // Handling the general IO exception from file reading
            System.err.println("A critical IO error occurred: " + e.getMessage());
        }
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
