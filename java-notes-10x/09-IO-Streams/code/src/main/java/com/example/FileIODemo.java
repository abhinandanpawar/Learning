package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * Main application to demonstrate modern Java I/O operations.
 */
public class FileIODemo {

    public static void main(String[] args) {
        System.out.println("--- Modern Java I/O Showcase ---");

        Path originalFile = Paths.get("original.txt");
        Path copiedFile = Paths.get("copied.txt");

        try {
            // 1. Write to a file using modern java.nio.file.Files
            System.out.println("\n1. Writing to file: " + originalFile);
            List<String> lines = Arrays.asList("Hello World,", "This is a test of modern Java I/O.", "Have a great day!");
            Files.write(originalFile, lines);
            System.out.println("   ...done.");

            // 2. Read from a file using a BufferedReader with try-with-resources
            System.out.println("\n2. Reading from file: " + originalFile);
            try (BufferedReader reader = Files.newBufferedReader(originalFile)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("   Read line: " + line);
                }
            }
            System.out.println("   ...done.");


            // 3. Copy a file using high-performance NIO Channels and Buffers
            System.out.println("\n3. Copying " + originalFile + " to " + copiedFile + " using NIO...");
            try (FileChannel sourceChannel = FileChannel.open(originalFile, StandardOpenOption.READ);
                 FileChannel destChannel = FileChannel.open(copiedFile, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (sourceChannel.read(buffer) > 0) {
                    // Flip the buffer to prepare for writing
                    buffer.flip();
                    destChannel.write(buffer);
                    // Clear the buffer to prepare for the next read
                    buffer.clear();
                }
            }
            System.out.println("   ...done.");
            System.out.println("   File copy successful: " + Files.exists(copiedFile));

        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Clean up the created files
            try {
                Files.deleteIfExists(originalFile);
                Files.deleteIfExists(copiedFile);
                System.out.println("\nCleanup complete.");
            } catch (IOException e) {
                System.err.println("Error during cleanup: " + e.getMessage());
            }
        }
    }
}
