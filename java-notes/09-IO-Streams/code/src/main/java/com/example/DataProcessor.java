package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Your mission, should you choose to accept it.
 */
public class DataProcessor {

    public static void main(String[] args) {
        Path inputFile = Paths.get("input.txt");
        Path outputFile = Paths.get("output.txt");

        // Your Mission:
        // 1. Read all lines from the 'inputFile' into a List of strings.
        //    Hint: Use Files.readAllLines().
        // 2. Process the list of lines:
        //    a. Convert each line to uppercase.
        //    b. Filter out any lines that contain the letter 'E'.
        //    Hint: Use Java Streams (list.stream()...).
        // 3. Write the processed lines to the 'outputFile'.
        //    Hint: Use Files.write().
        //
        // You will need to handle the IOException that these methods can throw.

        try {
            // --- Your code goes here ---


            // --- End of your code ---

            System.out.println("Data processing complete. Check output.txt!");

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
